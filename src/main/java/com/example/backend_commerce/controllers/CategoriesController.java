package com.example.backend_commerce.controllers;

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

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        System.out.println(category);

        String result = categoryService.insert(category);
        if (result.startsWith("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id).orElse(null);
        if (category != null) {
            categoryService.delete(category);
        }
    }
}
