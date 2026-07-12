package com.RoleBasedTasKManager.Project6.dto;

import com.RoleBasedTasKManager.Project6.entity.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse
{
    private Long id;

    private String task;

    private TaskStatus taskStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
