package com.gmail.kharchenko55.vlad.repository;

import com.gmail.kharchenko55.vlad.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
