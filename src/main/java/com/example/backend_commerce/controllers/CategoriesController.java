package com.example.backend_commerce.controllers;

import com.example.backend_commerce.dto.CategoryDTO;
import com.example.backend_commerce.handler.EntityNotFoundException;
import com.example.backend_commerce.handler.GlobalHandleException;
import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        if (categoryService.findCategoryById(id).isPresent()) {
            return ResponseEntity.ok(categoryService.findCategoryDTOById(id));
        }
        throw new EntityNotFoundException("Entity with ID " + id + " not found");
    }

    @GetMapping("")
    public List<CategoryDTO> getCategories() {
        return categoryService.getALLCategoryDTO();
    }

    @PostMapping("")
    public String createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.insert(categoryDTO);
    }

    @PutMapping("{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id).orElse(null);
        if (category != null) {
            categoryService.delete(category);
        }
    }
}
