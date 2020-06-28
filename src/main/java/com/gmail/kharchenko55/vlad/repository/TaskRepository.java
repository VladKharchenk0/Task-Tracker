package com.gmail.kharchenko55.vlad.repository;

import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.task.TaskStatus;
import com.gmail.kharchenko55.vlad.model.user.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Page<Task> findAllByTaskStatus(TaskStatus taskStatus, Pageable pageable);

    Page<Task> findAllByUser_UserStatus(UserStatus userStatus, Pageable pageable);

    Page<Task> findAll(Pageable pageable);
}
