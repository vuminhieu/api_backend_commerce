package com.example.backend_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDTO {

    private String title;
    private String vendor;
    private String category;

}
