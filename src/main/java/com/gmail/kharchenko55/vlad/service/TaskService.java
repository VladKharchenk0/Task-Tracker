package com.gmail.kharchenko55.vlad.service;

import com.gmail.kharchenko55.vlad.model.task.Task;

import java.util.Set;

public interface TaskService  {
    Task create (Task task);

    Task update(Task task);

    Task getById(Integer id);

    void delete(Integer id);

    Set<Task> getTasksByStatus(String status);

    Set<Task> getTasksByUserStatus(String userStatus);
}
