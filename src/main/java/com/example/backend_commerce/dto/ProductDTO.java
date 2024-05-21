package com.example.backend_commerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
