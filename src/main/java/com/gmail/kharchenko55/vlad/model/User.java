package com.gmail.kharchenko55.vlad.model;

import com.gmail.kharchenko55.vlad.common.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
@Component
@Table(name = "users")
public @Data class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}
