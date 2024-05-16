package com.example.backend_commerce.services;

import com.example.backend_commerce.models.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Optional<Image> findImageById(Long id);
    List<Image> findAll();
    String insert(Image image);
    Image update(Image image);
    void delete(Image image);
}
