package com.gmail.kharchenko55.vlad.security.jwt;

import com.gmail.kharchenko55.vlad.model.user.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user);
    }
}