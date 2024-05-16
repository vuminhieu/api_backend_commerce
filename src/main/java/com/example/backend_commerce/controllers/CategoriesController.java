package com.example.backend_commerce.controllers;

import com.example.backend_commerce.dto.CategoryDTO;
import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("{id}")
//    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
//        Optional<Category> category = categoryService.findCategoryById(id);
//        if (category.isPresent()) {
//            return ResponseEntity.ok(category.get());
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    @GetMapping("{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        return categoryService.findCategoryDTOById(id);
    }

    @GetMapping("")
    public List<CategoryDTO> getCategories() {
        return categoryService.getALLCategoryDTO();
    }

    @PostMapping("")
    public String createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.insert(categoryDTO);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id).orElse(null);
        if (category != null) {
            categoryService.delete(category);
        }
    }
}
