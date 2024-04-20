package com.example.backend_commerce.repositories;

import com.example.backend_commerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    public Long countById(Long id);
}
