package com.gmail.kharchenko55.vlad.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.kharchenko55.vlad.model.user.User;
import com.gmail.kharchenko55.vlad.model.user.UserStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @JsonIgnore
    private UserStatus userStatus;

    public User toUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    public static User fromUser(User user, Integer id) {
        User getUser = new User();
        getUser.setId(id);
        getUser.setFirstName(user.getFirstName());
        getUser.setLastName(user.getLastName());
        getUser.setEmail(user.getEmail());
        getUser.setUserStatus(user.getUserStatus());

        return getUser;
    }
}