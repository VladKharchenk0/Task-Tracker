package com.gmail.kharchenko55.vlad.service.impl;

import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.task.TaskStatus;
import com.gmail.kharchenko55.vlad.repository.TaskRepository;
import com.gmail.kharchenko55.vlad.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        task.setTaskStatus(TaskStatus.VIEW);
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Set<Task> getTasksByStatus(String status) {
        return null;
    }

    @Override
    public Set<Task> getTasksByUserStatus(String userStatus) {
        return null;
    }
}
