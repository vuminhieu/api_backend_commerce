package com.example.backend_commerce.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @Column(name = "body_html", nullable = false, length = 100000)
    private String body_html;

    @Column(name = "vendor", nullable = false, length = 250)
    private String vendor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @Column(name = "product_type", nullable = false, length = 250)
    private String product_type;

    @Column(name = "handle", nullable = false, unique = true, length = 1000)
    private String handle;

    @Column(name = "tags", nullable = false, length = 1000)
    private String tags;

    @Column(name = "status", nullable = false, length = 250)
    private String status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Image> images;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String updated_at;

    public Product(Long id, String title, String body_html, String vendor, String product_type, String handle, String tags, String status, List<Image> images, String created_at, String updated_at) {
        this.id = id;
        this.title = title;
        this.body_html = body_html;
        this.vendor = vendor;
        this.product_type = product_type;
        this.handle = handle;
        this.tags = tags;
        this.status = status;
        this.images = images;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
