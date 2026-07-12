package com.RoleBasedTasKManager.Project6.repository;

import com.RoleBasedTasKManager.Project6.entity.Task;
import com.RoleBasedTasKManager.Project6.entity.TaskStatus;
import com.RoleBasedTasKManager.Project6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository  extends JpaRepository<Task,Long>
{
    public List<Task> getTaskByUser(User user);
    public Optional<Task> findByIdAndUser(Long taskId,User user);
    public List<Task> findByUser(User user);
    public List<Task> findByUserAndTaskContainingIgnoreCase(User user,String task);
    public List<Task> findByUserAndTaskStatus(User user, TaskStatus taskStatus);
    public Long countByUser(User user);
    public Long countByUserAndTaskStatus(User user,TaskStatus taskStatus);
    public Long countByTaskStatus(TaskStatus taskStatus);
    public List<Task> findTop10ByOrderByCreatedAtDesc();


}
