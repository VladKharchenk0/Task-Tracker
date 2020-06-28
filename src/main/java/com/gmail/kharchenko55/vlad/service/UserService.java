package com.gmail.kharchenko55.vlad.service;

import com.gmail.kharchenko55.vlad.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User register(User user);

    Page<User> getAllUsers(Pageable pageable);

    User findByEmail(String email);

    User findById(Integer id);

    User update(User user);

    void delete(Integer id);
}
