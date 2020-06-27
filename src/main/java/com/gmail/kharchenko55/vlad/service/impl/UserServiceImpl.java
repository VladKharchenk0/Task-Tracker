package com.gmail.kharchenko55.vlad.service.impl;

import com.gmail.kharchenko55.vlad.model.User;
import com.gmail.kharchenko55.vlad.model.UserStatus;
import com.gmail.kharchenko55.vlad.repository.UserRepository;
import com.gmail.kharchenko55.vlad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(UserStatus.NEWCOMER);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        User currentUser = userRepository.getOne(user.getId());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(currentUser);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
