package com.example.backend_commerce.controllers;

import com.example.backend_commerce.dto.ProductDTO;
import com.example.backend_commerce.dto.SearchRequestDTO;
import com.example.backend_commerce.models.Product;
import com.example.backend_commerce.services.ProductService;
import com.example.backend_commerce.ultils.HandlesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public Map<String, Object> getProduct(@PathVariable Long id) {
        if (productService.findProductById(id).isPresent()) {
            ProductDTO productDTO = productService.findProductDTOById(id);
            return HandlesResponse.responseSuccess("", productDTO);
        } else {
            return new HashMap<String, Object>(HandlesResponse.ResponseError("Entity with ID " + id + " not found", "Entity not found"));
        }
    }

    @GetMapping("")
    public Map<String, Object> getProducts() {
        List<ProductDTO> data = productService.getAllProductDTO();
        return HandlesResponse.responseSuccess("", data);
    }

    @PostMapping("")
    public String createCategory(@RequestBody ProductDTO productDTO) {
        return productService.insert(productDTO);
    }

    @PutMapping("{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        Product product = productService.findProductById(id).orElse(null);
        if (product != null) {
            productService.delete(product);
        }
    }

    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody SearchRequestDTO searchRequest) {
        return productService.searchProducts(searchRequest.getTitle(),
                                             searchRequest.getVendor(), 
                                             searchRequest.getCategory());
    }
}
