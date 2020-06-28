package com.gmail.kharchenko55.vlad.model.task;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gmail.kharchenko55.vlad.common.BaseEntity;
import com.gmail.kharchenko55.vlad.model.user.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "tasks")
public @Data class Task extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;
}
