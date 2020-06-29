package com.gmail.kharchenko55.vlad.dto.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmail.kharchenko55.vlad.model.task.TaskStatus;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeStatusDto {
    private Integer id;
    private TaskStatus taskStatus;
}
