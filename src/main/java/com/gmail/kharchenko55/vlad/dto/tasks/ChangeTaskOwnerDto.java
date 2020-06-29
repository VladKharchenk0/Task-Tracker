package com.gmail.kharchenko55.vlad.dto.tasks;

import lombok.Data;

@Data
public class ChangeTaskOwnerDto {
    private Integer taskId;
    private Integer newOwnerId;
}
