package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.dto.TaskDto;
import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.service.TaskService;
import com.gmail.kharchenko55.vlad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
