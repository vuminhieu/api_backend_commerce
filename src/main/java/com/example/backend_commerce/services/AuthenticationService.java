package com.example.backend_commerce.services;

import com.example.backend_commerce.auth.AuthenticationReponse;
import com.example.backend_commerce.auth.AuthenticationRequest;
import com.example.backend_commerce.models.Role;
import com.example.backend_commerce.models.User;
import com.example.backend_commerce.repositories.RoleCustomRepository;
import com.example.backend_commerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final RoleCustomRepository roleCustomRepository;

    @Autowired
    private  final JwtService jwtService;


    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        List<Role> roles = null;
        if (user != null) {
            roles = roleCustomRepository.getRolesByUserEmail(user);
        }

        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        Set<Role> set = new HashSet<>();
        roles.stream().forEach(c -> {
            set.add(new Role(c.getName()));
        });
        user.setRoles(set);
        set.stream().forEach(i -> {
            authorities.add(new SimpleGrantedAuthority(i.getName()));
        });

        var JwtToken = jwtService.generateToken(user, authorities);
        var refreshToken = jwtService.generateRefreshToken(user, authorities);
        return AuthenticationReponse.builder().token(JwtToken).refreshToken(refreshToken).build();

    }

}
