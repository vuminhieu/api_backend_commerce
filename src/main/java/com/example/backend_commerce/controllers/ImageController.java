package com.example.backend_commerce.controllers;

import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.models.Image;
import com.example.backend_commerce.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/v1/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("")
    public List<Image> getCategories() {
        return imageService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> image = imageService.findImageById(id);
        if (image.isPresent()) {
            return ResponseEntity.ok(image.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        System.out.println(image);
        String result = imageService.insert(image);
        if (result.startsWith("error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(image);
    }
}
