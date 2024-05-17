package com.example.backend_commerce.dto;

import com.example.backend_commerce.models.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private String handle;
    private String tags;
    private String status;
    private List<ImageDTO> images;
    private String created_at;
    private String updated_at;

    public ProductDTO(Long id, String title, String body_html, String vendor, String product_type, String handle, String tags, String status, List<ImageDTO> images, String created_at, String updated_at) {
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

    public ProductDTO() {
    }
}
