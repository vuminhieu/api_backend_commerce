package com.example.backend_commerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "images")
@Setter
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // map categories to images
    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id")
    private Category category;

    // map products to images
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_id")
    private Product product;

    public Image() {
    }

    public Image(long id, Category category, Integer position, double width, double height, String src, String created_at, String updated_at) {
        this.id = id;
        this.category = category;
        this.position = position;
        this.width = width;
        this.height = height;
        this.src = src;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Column(name = "position")
    private Integer position;



    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "src", nullable = false, length = 1000)
    private String src;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_at;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String updated_at;


}
