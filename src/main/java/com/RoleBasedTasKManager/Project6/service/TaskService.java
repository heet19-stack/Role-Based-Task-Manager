package com.RoleBasedTasKManager.Project6.service;

import com.RoleBasedTasKManager.Project6.dto.TaskRequest;
import com.RoleBasedTasKManager.Project6.dto.TaskResponse;
import com.RoleBasedTasKManager.Project6.entity.Task;
import com.RoleBasedTasKManager.Project6.entity.TaskStatus;
import com.RoleBasedTasKManager.Project6.entity.User;
import com.RoleBasedTasKManager.Project6.repository.TaskRepository;
import com.RoleBasedTasKManager.Project6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService
{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public TaskResponse createTask(TaskRequest request,String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        Task task = new Task();

        task.setTask(request.getTask());
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(
          savedTask.getId(),
          savedTask.getTask(),
                savedTask.getTaskStatus(),
                savedTask.getCreatedAt(),
                savedTask.getUpdatedAt()
        );
    }

    public List<TaskResponse> getTaskByUser(String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        List<Task> tasks = taskRepository.findByUser(user);

        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks){
            TaskResponse response = new TaskResponse(
                    task.getId(),
                    task.getTask(),
                    task.getTaskStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
            responses.add(response);
        }
        return responses;
    }

    public String deleteTask(Long taskId,String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()->new RuntimeException("Task not found!"));

        taskRepository.delete(task);

        return "Task deleted successfully!";
    }

    public TaskResponse updateTask(Long taskId, TaskRequest request,String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()->new RuntimeException("Task not found!"));

        task.setTask(request.getTask());

        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTask(),
                savedTask.getTaskStatus(),
                savedTask.getCreatedAt(),
                savedTask.getUpdatedAt()
        );
    }
    public TaskResponse updateTaskStatus(Long taskId,TaskStatus taskStatus,String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()->new RuntimeException("Task not found"));

        task.setTaskStatus(taskStatus);
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTask(),
                savedTask.getTaskStatus(),
                savedTask.getCreatedAt(),
                savedTask.getUpdatedAt()
        );
    }

    public TaskResponse getSingleTask(Long taskId,String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()->new RuntimeException("Task not found"));

        return new TaskResponse(
         task.getId(),
         task.getTask(),
         task.getTaskStatus(),
         task.getCreatedAt(),
         task.getUpdatedAt()
        );
    }

    public List<TaskResponse> searchTask(String keyword,String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        List<Task> tasks = taskRepository.
                findByUserAndTaskContainingIgnoreCase(user,keyword);

        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks){
            TaskResponse response = new TaskResponse(
                    task.getId(),
                    task.getTask(),
                    task.getTaskStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
            responses.add(response);
        }
        return responses;
    }

    public List<TaskResponse> filterByTask(TaskStatus taskStatus,String email){
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found!");
        }

//   here was the mistake : List<Task> tasks = taskRepository.findByUserAndTaskStatus(user,TaskStatus.PENDING);

        List<Task> tasks = taskRepository.findByUserAndTaskStatus(user,taskStatus);

        ArrayList<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks){
            TaskResponse response = new TaskResponse(
                    task.getId(),
                    task.getTask(),
                    task.getTaskStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt()
            );
            responses.add(response);
        }
        return responses;
    }
}