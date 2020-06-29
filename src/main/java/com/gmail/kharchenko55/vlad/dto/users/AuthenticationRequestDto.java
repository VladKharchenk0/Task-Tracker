package com.gmail.kharchenko55.vlad.dto.users;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}