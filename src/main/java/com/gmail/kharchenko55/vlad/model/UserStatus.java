package com.gmail.kharchenko55.vlad.model;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {
    NEWCOMER("NEWCOMER"),
    OLD_USER("OLD_USER");

    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Optional<UserStatus> getUserStatus(String status) {
        return Arrays.stream(UserStatus.values())
                .filter(enumValue -> enumValue.getStatus().equals(status))
                .findAny();
    }
}