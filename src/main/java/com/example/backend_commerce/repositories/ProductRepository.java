package com.example.backend_commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend_commerce.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Long countById(Long id);

     @Query("SELECT p FROM Product p WHERE " +
           "(:name IS NULL OR p.title LIKE %:title%) AND " +
           "(:vendor IS NULL OR p.vendor = :vendor) AND " +
           "(:category IS NULL OR :category MEMBER OF p.categories)")
    List<Product> searchProducts(@Param("title") String title, 
                                 @Param("vendor") String vendor, 
                                 @Param("category") String category);
}
