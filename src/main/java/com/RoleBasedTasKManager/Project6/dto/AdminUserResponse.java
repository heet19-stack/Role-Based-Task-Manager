package com.RoleBasedTasKManager.Project6.dto;

import com.RoleBasedTasKManager.Project6.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserResponse
{
    private Long id;
    private String username;
    private Role role;
    private Long totalTasks;
    private Long completedTasks;
    private Long pendingTasks;
    private LocalDateTime createdAt;
}
