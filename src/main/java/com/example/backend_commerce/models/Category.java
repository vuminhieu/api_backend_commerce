package com.example.backend_commerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "handle" ,nullable = false, unique = true, length = 1000)
    private String handle;

    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Image> images;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products =  new HashSet<>();

    @Column(name = "description", length = 100000)
    private String description;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String updated_at;

    public Category(long id, String handle, String title, List<Image> images, String description, String created_at, String updated_at) {
        this.id = id;
        this.handle = handle;
        this.title = title;
        this.images = images;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Category() {
    }
}
