package com.gmail.kharchenko55.vlad.service.impl;

import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.task.TaskStatus;
import com.gmail.kharchenko55.vlad.model.user.UserStatus;
import com.gmail.kharchenko55.vlad.repository.TaskRepository;
import com.gmail.kharchenko55.vlad.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return taskRepository.save(task);
    }

    @Override
    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Page<Task> getTasksByStatus(TaskStatus status, Pageable pageable) {
        return taskRepository.findAllByTaskStatus(status, pageable);
    }

    @Override
    public Page<Task> getTasksByUserStatus(UserStatus userStatus, Pageable pageable) {
        return taskRepository.findAllByUser_UserStatus(userStatus, pageable);
    }
}
