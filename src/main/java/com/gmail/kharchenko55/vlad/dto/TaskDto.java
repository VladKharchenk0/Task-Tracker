package com.gmail.kharchenko55.vlad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.kharchenko55.vlad.model.task.Task;
import com.gmail.kharchenko55.vlad.model.task.TaskStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {
    private Integer id;
    private String title;
    private String description;
    private TaskStatus taskStatus;

    public Task toTask() {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);

        return task;
    }
}
