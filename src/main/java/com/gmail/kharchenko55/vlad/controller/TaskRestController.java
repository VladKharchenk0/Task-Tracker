package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.dto.TaskDto;
import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.service.TaskService;
import com.gmail.kharchenko55.vlad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/tasks")
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
    public ResponseEntity updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskService.getById(taskDto.getId());
        if (task==null){
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
}
