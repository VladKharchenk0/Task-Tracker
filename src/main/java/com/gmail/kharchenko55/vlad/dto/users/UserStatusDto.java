package com.gmail.kharchenko55.vlad.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.kharchenko55.vlad.model.user.UserStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStatusDto {
    private UserStatus userStatus;
}
