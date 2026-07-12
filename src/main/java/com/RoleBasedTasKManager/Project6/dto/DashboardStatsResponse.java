package com.RoleBasedTasKManager.Project6.dto;

import com.RoleBasedTasKManager.Project6.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsResponse
{
    private Long totalEmployees;
    private Long totalTasks;
    private Long completedTasks;
    private Long pendingTasks;
    private List<LatestEmployeeResponse> latestEmployees;
}
