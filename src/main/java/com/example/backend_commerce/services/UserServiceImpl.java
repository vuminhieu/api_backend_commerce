package com.example.backend_commerce.services;

import com.example.backend_commerce.models.Role;
import com.example.backend_commerce.models.User;
import com.example.backend_commerce.repositories.RoleRepository;
import com.example.backend_commerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByEmail(username).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}
