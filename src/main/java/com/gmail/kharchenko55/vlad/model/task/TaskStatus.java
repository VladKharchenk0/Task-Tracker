package com.gmail.kharchenko55.vlad.model.task;

import java.util.Arrays;
import java.util.Optional;

public enum TaskStatus {
    VIEW("VIEW"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Optional<TaskStatus> getTaskStatus(String status) {
        return Arrays.stream(TaskStatus.values())
                .filter(enumValue -> enumValue.getStatus().equals(status))
                .findAny();
    }
}
