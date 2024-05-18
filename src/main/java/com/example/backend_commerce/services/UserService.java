package com.example.backend_commerce.services;

import com.example.backend_commerce.models.Role;
import com.example.backend_commerce.models.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);

}
