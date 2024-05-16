package com.example.backend_commerce.repositories;

import com.example.backend_commerce.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    public Long countById(Long id);
}
