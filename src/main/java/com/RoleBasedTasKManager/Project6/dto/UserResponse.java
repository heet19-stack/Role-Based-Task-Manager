package com.RoleBasedTasKManager.Project6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse
{
    private Long id;

    private String username;

    private String email;

    private Long taskCount;
}
