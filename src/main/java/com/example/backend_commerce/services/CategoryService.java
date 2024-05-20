package com.example.backend_commerce.services;

import com.example.backend_commerce.dto.CategoryDTO;
import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.models.Image;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Optional<Category> findCategoryById(Long id);
    CategoryDTO findCategoryDTOById(Long id);
    List<Category> findAll();
    String update(Long id, CategoryDTO categoryDTO);
    void delete(Category category);
    List<CategoryDTO> getALLCategoryDTO();
    String insert(CategoryDTO categoryDTO);

}
