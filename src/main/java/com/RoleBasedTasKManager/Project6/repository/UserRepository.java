package com.RoleBasedTasKManager.Project6.repository;

import com.RoleBasedTasKManager.Project6.entity.Role;
import com.RoleBasedTasKManager.Project6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository  extends JpaRepository<User,Long>
{
    public User findByEmail(String email);
    public List<User> findByRole(Role role);
    public Long countByRole(Role role);
    public List<User> findTop5ByRoleOrderByCreatedAtDesc(Role role);
    public List<User> findByRoleAndUsernameContainingIgnoreCaseOrRoleAndEmailContainingIgnoreCase(
            Role role1,String username,Role role2,String email);


}
