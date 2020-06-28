package com.gmail.kharchenko55.vlad.service;

import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.task.TaskStatus;
import com.gmail.kharchenko55.vlad.model.user.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task create(Task task);

    Task update(Task task);

    Task getById(Integer id);

    void delete(Integer id);

    Page<Task> getAllTasks(Pageable pageable);

    Page<Task> getTasksByStatus(TaskStatus status, Pageable pageable);

    Page<Task> getTasksByUserStatus(UserStatus userStatus, Pageable pageable);

    Page<Task> findTaskByUserId(Integer id, Pageable pageable);

}
