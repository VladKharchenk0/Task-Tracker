package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.dto.UserDto;
import com.gmail.kharchenko55.vlad.model.User;
import com.gmail.kharchenko55.vlad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing == null) {
            throw new IllegalArgumentException(String.format("User with %s email doesnt exists", userDto.getEmail()));
        }
        User user = userDto.toUser();
        user.setId(existing.getId());
        userService.update(user);

        return ResponseEntity.ok(String.format("User %s %s successfully updated",
                userDto.getFirstName(), userDto.getLastName()));
    }

    @GetMapping(value = "delete/{id}")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);

        return ResponseEntity.ok(String.format("User %s %s successfully deleted",
                user.getFirstName(), user.getLastName()));
    }
}
