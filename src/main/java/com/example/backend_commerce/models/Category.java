package com.example.backend_commerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;


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

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description" ,nullable = true, length = 10000)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Time created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Time updated_at;

    public Category(long id, String handle, String title, String description, String image, Time created_at, Time updated_at) {
        this.id = id;
        this.handle = handle;
        this.title = title;
        this.description = description;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Category() {
    }
}
