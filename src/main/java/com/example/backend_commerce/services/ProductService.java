package com.example.backend_commerce.services;


import java.util.List;
import java.util.Optional;

import com.example.backend_commerce.dto.ProductDTO;
import com.example.backend_commerce.models.Product;

public interface ProductService {
    Optional<Product> findProductById(Long id);
    ProductDTO findProductDTOById(Long id);
    List<Product> findAll();
    String update(Long id, ProductDTO productDTO);
    void delete(Product product);
    List<ProductDTO> getAllProductDTO();
    String insert(ProductDTO productDTO);
    List<Product> searchProducts(String title, String vendor, String category);

}
