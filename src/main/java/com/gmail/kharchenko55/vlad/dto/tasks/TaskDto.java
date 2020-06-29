package com.gmail.kharchenko55.vlad.dto.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.kharchenko55.vlad.model.task.Task;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {
    private String title;
    private String description;

    public Task toTask() {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);

        return task;
    }
}
