package com.gmail.kharchenko55.vlad.dto.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskDto {
    private Integer id;
    private String title;
    private String description;
}
