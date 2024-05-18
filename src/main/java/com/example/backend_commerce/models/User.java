package com.example.backend_commerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(unique = true, length = 45)
    private String username;

    @Column(length = 64)
    private String password;

    @Column(unique = true, length = 250)
    private String email;

    @Column(name = "first_name", length = 45)
    private String first_name;

    @Column(name = "last_name", length = 45)
    private String last_name;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "address", length = 45)
    private String address;

    @Column(name = "order_count")
    private Integer order_count;

    @Column(name = "state", length = 45)
    private String state;

    @Column(name = "note", length = 10000)
    private String note;

    @Column(name = "tags", length = 200)
    private String tags;

    @Column(name = "currency", length = 45)
    private String currency;

//    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private String created_at;
//
//    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private String updated_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }


    public User(Long id, String username, String password, String email, String first_name, String last_name, String phone, String address, Integer order_count, String state, String note, String tags, String currency) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.order_count = order_count;
        this.state = state;
        this.note = note;
        this.tags = tags;
        this.currency = currency;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
