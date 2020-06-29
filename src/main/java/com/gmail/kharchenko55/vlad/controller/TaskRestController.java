package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.dto.tasks.*;
import com.gmail.kharchenko55.vlad.dto.users.UserStatusDto;
import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.service.TaskService;
import com.gmail.kharchenko55.vlad.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/tasks")
@Api(description = "Task level resources")
public class TaskRestController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskRestController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskDto taskDto, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Task task = taskDto.toTask();
        task.setUser(user);

        taskService.create(task);

        return ResponseEntity.ok(String.format("Task %s for %s successfully created",
                task.getTitle(), principal.getName()));
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestBody UpdateTaskDto taskDto) {
        Task task = taskService.getById(taskDto.getId());
        if (task == null) {
            throw new IllegalArgumentException(String.format("Task with id %d doesnt exists", taskDto.getId()));
        }

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());

        taskService.update(task);
        return ResponseEntity.ok(String.format("Task %s successfully updated",
                taskDto.getTitle()));
    }

    @GetMapping(value = "delete/{id}")
    public ResponseEntity deleteTaskById(@PathVariable(name = "id") Integer id) {
        Task task = taskService.getById(id);

        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.delete(id);

        return ResponseEntity.ok(String.format("Task %s successfully deleted",
                task.getTitle()));
    }

    @PutMapping(value = "changeStatus")
    public ResponseEntity changeTaskStatus(@RequestBody ChangeStatusDto taskDto) {
        Task task = taskService.getById(taskDto.getId());
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (taskDto.getTaskStatus().equals(task.getTaskStatus())) {
            return ResponseEntity.ok(String.format("Task %s already has %s status",
                    task.getTitle(), task.getTaskStatus()));
        }

        task.setTaskStatus(taskDto.getTaskStatus());
        taskService.update(task);

        return ResponseEntity.ok(String.format("Task %s successfully changed status to %s",
                task.getTitle(), task.getTaskStatus()));
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAllTasks(@PageableDefault(size = 10, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Task> page = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "getByTaskStatus")
    public ResponseEntity<?> getByTaskStatus(@RequestBody GetByStatusDto taskDto,
                                             @PageableDefault(size = 10, sort = {"id"},
                                                     direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Task> tasks = taskService.getTasksByStatus(taskDto.getTaskStatus(), pageable);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "getByUserStatus")
    public ResponseEntity<?> getByUserStatus(@RequestBody UserStatusDto userDto,
                                             @PageableDefault(size = 10, sort = {"id"},
                                                     direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Task> tasks = taskService.getTasksByUserStatus(userDto.getUserStatus(), pageable);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "getByUserId/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable(name = "id") Integer id,
                                         @PageableDefault(size = 10, sort = {"id"},
                                                 direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Task> tasks = taskService.findTaskByUserId(id, pageable);
        if (tasks == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tasks);
    }

    @PutMapping(value = "changeTaskOwner")
    public ResponseEntity changeTaskOwner(@RequestBody ChangeTaskOwnerDto changeTaskOwnerDto,
                                          Principal principal) {
        User previousOwner = userService.findByEmail(principal.getName());
        User newOwner = userService.findById(changeTaskOwnerDto.getNewOwnerId());
        Integer taskId = changeTaskOwnerDto.getTaskId();

        Task task = taskService.getById(taskId);
        if (task == null) {
            return ResponseEntity.badRequest().body(String.format("Task with id %d not found", taskId));
        }

        if (!previousOwner.getTasks().contains(task)) {
            return ResponseEntity.badRequest().body("You are not the owner of this task");
        }

        task.setUser(newOwner);
        taskService.update(task);

        return ResponseEntity.ok(String.format("For task %s set new owner -> %s",
                task.getTitle(), newOwner.getEmail()));
    }
}