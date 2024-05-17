package com.example.backend_commerce.services;


import com.example.backend_commerce.dto.ProductDTO;
import com.example.backend_commerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findProductById(Long id);
    ProductDTO findCategoryDTOById(Long id);
    List<Product> findAll();
    String update(Long id, ProductDTO productDTO);
    void delete(Product product);
    List<ProductDTO> getAllProductDTO();
    String insert(ProductDTO productDTO);

}
