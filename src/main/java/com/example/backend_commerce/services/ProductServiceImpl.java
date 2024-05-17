package com.example.backend_commerce.services;

import com.example.backend_commerce.dto.ProductDTO;
import com.example.backend_commerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductDTO findCategoryDTOById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public String update(Long id, ProductDTO productDTO) {
        return "";
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public List<ProductDTO> getAllProductDTO() {
        return List.of();
    }

    @Override
    public String insert(ProductDTO productDTO) {
        return "";
    }
}
