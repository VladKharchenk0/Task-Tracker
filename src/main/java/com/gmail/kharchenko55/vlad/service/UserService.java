package com.gmail.kharchenko55.vlad.service;

import com.gmail.kharchenko55.vlad.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAllUsers();

    User findByEmail(String email);

    User findById(Integer id);

    User update(User user);

    void delete(Integer id);
}
