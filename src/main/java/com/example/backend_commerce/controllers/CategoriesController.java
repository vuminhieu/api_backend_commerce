package com.example.backend_commerce.controllers;

import com.example.backend_commerce.dto.CategoryDTO;
import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.services.CategoryService;
import com.example.backend_commerce.ultils.HandlesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("{id}")
    public Map<String, Object> getCategory(@PathVariable Long id) {
        if (categoryService.findCategoryById(id).isPresent()) {
            CategoryDTO categoryDTO = categoryService.findCategoryDTOById(id);
            return HandlesResponse.responseSuccess("", categoryDTO);
        } else {
            return new HashMap<String, Object>(HandlesResponse.ResponseError("Entity with ID " + id + " not found", "Entity not found"));
        }
    }

    @GetMapping("")
    public Map<String, Object> getCategories() {
        List<CategoryDTO> data = categoryService.getALLCategoryDTO();
        return HandlesResponse.responseSuccess("", data);
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
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        Category category = categoryService.findCategoryById(id).orElse(null);
        if (category != null) {
            categoryService.delete(category);
        }
    }
}
