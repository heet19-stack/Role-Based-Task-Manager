package com.RoleBasedTasKManager.Project6.controller;

import com.RoleBasedTasKManager.Project6.dto.*;
import com.RoleBasedTasKManager.Project6.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @GetMapping("/employees")
    public ResponseEntity<List<UserResponse>> getEmployees(){
        return ResponseEntity.ok(adminService.getEmployees());
    }
    @GetMapping("/employees/{userId}/tasks")
    public ResponseEntity<List<TaskResponse>> getTaskById(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.getTaskByUserId(userId));
    }
    @GetMapping("/employees/{userId}")
    public ResponseEntity<AdminUserResponse> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.getUserById(userId));
    }
    @GetMapping("/employees/{userId}/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getSingleTaskByUserId(@PathVariable Long userId,@PathVariable Long taskId){
        return ResponseEntity.ok(adminService.getSingleTaskByUserId(userId,taskId));
    }
    @GetMapping("/employees/{userId}/tasks/completed")
    public ResponseEntity<List<TaskResponse>> getTaskCompleted(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.getTaskCompleted(userId));
    }
    @GetMapping("/employees/{userId}/tasks/pending")
    public ResponseEntity<List<TaskResponse>> getTaskPending(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.getTaskPending(userId));
    }
    @PostMapping("/employees/{userId}/tasks")
    public ResponseEntity<TaskResponse> allotTask(@PathVariable Long userId,@RequestBody TaskRequest request){
        return ResponseEntity.ok(adminService.allotTask(userId,request));
    }
    @PostMapping("/employees/{userId}/tasks/bulk")
    public ResponseEntity<List<TaskResponse>> allotBulkTask(@PathVariable Long userId,@RequestBody List<TaskRequest> requests){
        return ResponseEntity.ok(adminService.allotBulkTask(userId,requests));
    }
    @DeleteMapping("/employees/{userId}/tasks/{taskId}")
    public ResponseEntity<String> deleteTaskByUserId(@PathVariable Long userId,@PathVariable Long taskId){
        return ResponseEntity.ok(adminService.deleteTaskByUserId(userId,taskId));
    }
    @PutMapping("/employees/{userId}/tasks/{taskId}")
    public ResponseEntity<TaskResponse> updateTaskByUserId(@PathVariable Long userId,@PathVariable Long taskId,@RequestBody TaskRequest request){
        return ResponseEntity.ok(adminService.updateTaskByUserId(userId,taskId,request));
    }
    @GetMapping("/dashboard/stats")
    public ResponseEntity<DashboardStatsResponse> getAdminDashboard(){
        return ResponseEntity.ok(adminService.getAdminDashboard());
    }
    @PutMapping("/employees/{userId}/disable")
    public ResponseEntity<String> disableUserById(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.disableUserById(userId));
    }
    @PutMapping("/employees/{userId}/enable")
    public ResponseEntity<String> enableUserById(@PathVariable Long userId){
        return ResponseEntity.ok(adminService.enableUserById(userId));
    }
    @GetMapping("/employees/search")
    public ResponseEntity<List<UserResponse>> searchEmployees(@RequestParam String keyword){
        return ResponseEntity.ok(adminService.searchEmployees(keyword));
    }
    @GetMapping("/tasks/recent")
    public ResponseEntity<List<TaskResponse>> getRecentTasks(){
        return ResponseEntity.ok(adminService.getRecentTasks());
    }
}