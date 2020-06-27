package com.gmail.kharchenko55.vlad.controller;

import com.gmail.kharchenko55.vlad.dto.AuthenticationRequestDto;
import com.gmail.kharchenko55.vlad.dto.UserDto;
import com.gmail.kharchenko55.vlad.model.User;
import com.gmail.kharchenko55.vlad.security.jwt.JwtTokenProvider;
import com.gmail.kharchenko55.vlad.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email);

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody UserDto userDto) {
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            throw new IllegalArgumentException(String.format("User with %s email already exists", userDto.getEmail()));
        }
        User user = userDto.toUser();
       userService.register(user);

        return ResponseEntity.ok(String.format("User %s %s successfully added",
                userDto.getFirstName(), userDto.getLastName()));
    }
}

