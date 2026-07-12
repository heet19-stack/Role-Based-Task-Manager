package com.RoleBasedTasKManager.Project6.controller;

import com.RoleBasedTasKManager.Project6.dto.TaskRequest;
import com.RoleBasedTasKManager.Project6.dto.TaskResponse;
import com.RoleBasedTasKManager.Project6.entity.Task;
import com.RoleBasedTasKManager.Project6.entity.TaskStatus;
import com.RoleBasedTasKManager.Project6.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@PreAuthorize("hasAnyRole('EMPLOYEE')")
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request, Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.createTask(request,email));
    }
    @GetMapping("/getTask")
    public ResponseEntity<List<TaskResponse>> getTaskById(Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.getTaskByUser(email));
    }
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId,Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.deleteTask(taskId,email));
    }
    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId,@RequestBody TaskRequest request,Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.updateTask(taskId,request,email));
    }
    @PutMapping("/updateStatus/{taskId}")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long taskId,@RequestParam TaskStatus taskStatus,Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId,taskStatus,email));
    }
    @GetMapping("/getSingleTask/{taskId}")
    public ResponseEntity<TaskResponse> getSingleTask(@PathVariable Long taskId,Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.getSingleTask(taskId,email));
    }
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTask(@RequestParam String keyword,Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.searchTask(keyword,email));
    }
    @GetMapping("/filter")
    public ResponseEntity<List<TaskResponse>> filterByStatus(@RequestParam TaskStatus taskStatus,Authentication authentication){
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.filterByTask(taskStatus,email));
    }
}
