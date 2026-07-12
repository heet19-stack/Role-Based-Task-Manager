package com.RoleBasedTasKManager.Project6.service;

import com.RoleBasedTasKManager.Project6.dto.*;
import com.RoleBasedTasKManager.Project6.entity.Role;
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
public class AdminService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<UserResponse> getEmployees(){

        List<User> users = userRepository.findByRole(Role.ROLE_EMPLOYEE);

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users){
            Long taskCount = taskRepository.countByUser(user);

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    taskCount
            );
            responses.add(response);
        }
        return responses;
    }

   public List<TaskResponse> getTaskByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

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

   public AdminUserResponse getUserById(Long userId){
       User user = userRepository.findById(userId)
               .orElseThrow(()->new RuntimeException("User not found!"));

       Long taskCount = taskRepository.countByUser(user);
       Long completedTask = taskRepository.countByUserAndTaskStatus(user, TaskStatus.COMPLETED);
       Long pendingTask = taskRepository.countByUserAndTaskStatus(user, TaskStatus.PENDING);

         AdminUserResponse response = new AdminUserResponse();

         response.setId(user.getId());
         response.setUsername(user.getUsername());
         response.setRole(user.getRole());
         response.setCompletedTasks(completedTask);
         response.setPendingTasks(pendingTask);
         response.setCreatedAt(user.getCreatedAt());

       return response;
   }

    public TaskResponse getSingleTaskByUserId(Long userId,Long taskId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()-> new RuntimeException("Task not found!"));

        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTask(task.getTask());
        response.setTaskStatus(task.getTaskStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());

        return response;
    }

    public List<TaskResponse> getTaskCompleted(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        List<Task> tasks = taskRepository
                .findByUserAndTaskStatus(user,TaskStatus.COMPLETED);

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

    public List<TaskResponse> getTaskPending(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        List<Task> tasks = taskRepository
                .findByUserAndTaskStatus(user,TaskStatus.PENDING);

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

    public TaskResponse allotTask(Long userId, TaskRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        Task task = new Task();
        task.setTask(request.getTask() + "(By admin)");
        task.setTaskStatus(TaskStatus.PENDING);
        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        taskRepository.save(task);

        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTask(task.getTask());
        response.setTaskStatus(task.getTaskStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());

        return response;
    }

    public List<TaskResponse> allotBulkTask(Long userId,List<TaskRequest> requests){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        List<TaskResponse> responses = new ArrayList<>();

        for (TaskRequest request : requests){
            Task task = new Task();
            task.setTask(request.getTask() + "(By admin)");
            task.setTaskStatus(TaskStatus.PENDING);
            task.setUser(user);
            task.setCreatedAt(LocalDateTime.now());

            Task savedTask = taskRepository.save(task);

            TaskResponse response = new TaskResponse();

            response.setId(savedTask.getId());
            response.setTask(savedTask.getTask());
            response.setTaskStatus(savedTask.getTaskStatus());
            response.setCreatedAt(savedTask.getCreatedAt());
            response.setUpdatedAt(savedTask.getUpdatedAt());

            responses.add(response);
        }
        return responses;
    }

    public String deleteTaskByUserId(Long userId,Long taskId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()->new RuntimeException("Task not found!"));

        taskRepository.delete(task);

        return "Task deleted successfully";
    }

    public TaskResponse updateTaskByUserId(Long userId,Long taskId,TaskRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        Task task = taskRepository.findByIdAndUser(taskId,user)
                .orElseThrow(()->new RuntimeException("Task not found!"));

        task.setTask(request.getTask() + "(By admin)");
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        TaskResponse response = new TaskResponse();

        response.setId(savedTask.getId());
        response.setTask(savedTask.getTask());
        response.setTaskStatus(savedTask.getTaskStatus());
        response.setCreatedAt(savedTask.getCreatedAt());
        response.setUpdatedAt(savedTask.getUpdatedAt());

        return response;
    }

    public DashboardStatsResponse getAdminDashboard(){
        Long totalEmployees = userRepository.countByRole(Role.ROLE_EMPLOYEE);
        Long totalTasks = taskRepository.count();
        Long completedTasks = taskRepository.countByTaskStatus(TaskStatus.COMPLETED);
        Long pendingTasks = taskRepository.countByTaskStatus(TaskStatus.PENDING);
        List<User> recentUser = userRepository.findTop5ByRoleOrderByCreatedAtDesc(Role.ROLE_EMPLOYEE);

        List<LatestEmployeeResponse> recentUsers = new ArrayList<>();

        for (User user :  recentUser){
            LatestEmployeeResponse employeeResponse = new LatestEmployeeResponse();
            employeeResponse.setId(user.getId());
            employeeResponse.setUsername(user.getUsername());

            recentUsers.add(employeeResponse);
        }

        DashboardStatsResponse response = new DashboardStatsResponse();

        response.setTotalEmployees(totalEmployees);
        response.setTotalTasks(totalTasks);
        response.setCompletedTasks(completedTasks);
        response.setPendingTasks(pendingTasks);
        response.setLatestEmployees(recentUsers);

        return response;
    }

    public String disableUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        user.setEnabled(false);
        userRepository.save(user);
        return "User disabled successfully";
    }

    public String enableUserById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found!"));

        user.setEnabled(true);
        userRepository.save(user);
        return "User enabled successfully";
    }

    public List<UserResponse> searchEmployees(String keyword){
        List<User> users = userRepository.findByRoleAndUsernameContainingIgnoreCaseOrRoleAndEmailContainingIgnoreCase(
                Role.ROLE_EMPLOYEE,
                keyword,
                Role.ROLE_EMPLOYEE,
                keyword
        );

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users){
            Long taskCount = taskRepository.countByUser(user);
            UserResponse response = new UserResponse();

            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setTaskCount(taskCount);

            responses.add(response);
        }
       return responses;
    }

    public List<TaskResponse> getRecentTasks(){

        List<Task> tasks =
                taskRepository.findTop10ByOrderByCreatedAtDesc();

        List<TaskResponse> responses = new ArrayList<>();

        for(Task task : tasks){

            TaskResponse response = new TaskResponse();

            response.setId(task.getId());
            response.setTask(task.getTask());
            response.setTaskStatus(task.getTaskStatus());
            response.setCreatedAt(task.getCreatedAt());
            response.setUpdatedAt(task.getUpdatedAt());

            responses.add(response);
        }

        return responses;
    }
    }