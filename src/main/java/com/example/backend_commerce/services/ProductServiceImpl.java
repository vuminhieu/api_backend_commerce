package com.example.backend_commerce.services;

import com.example.backend_commerce.dto.CategoryDTO;
import com.example.backend_commerce.dto.ImageDTO;
import com.example.backend_commerce.dto.ProductDTO;
import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.models.Image;
import com.example.backend_commerce.models.Product;
import com.example.backend_commerce.repositories.ImageRepository;
import com.example.backend_commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ImageRepository imageRepo;

    @Override
    public Optional<Product> findProductById(Long id) {
        if (productRepo.countById(id) == 0) {
            return Optional.empty();
        }

        return productRepo.findById(id);
    }
    private ProductDTO convertFindByIdDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setHandle(product.getHandle());
        productDTO.setTitle(product.getTitle());
        productDTO.setBody_html(product.getBody_html());
        productDTO.setVendor(product.getVendor());
        productDTO.setProduct_type(product.getProduct_type());
        productDTO.setTags(product.getTags());
        productDTO.setStatus(product.getStatus());
        productDTO.setCreated_at(product.getCreated_at());
        productDTO.setUpdated_at(product.getUpdated_at());
        List<ImageDTO> imageDTOs = product.getImages().stream().map(image -> {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(image.getId());
            imageDTO.setSrc(image.getSrc());
            imageDTO.setHeight(image.getHeight());
            imageDTO.setWidth(image.getWidth());
            imageDTO.setCreated_at(image.getCreated_at());
            imageDTO.setUpdated_at(image.getUpdated_at());
            return imageDTO;
        }).collect(Collectors.toList());

        productDTO.setImages(imageDTOs);
        return productDTO;
    }

    @Override
    public ProductDTO findProductDTOById(Long id) {
        Optional<Product> productOpt = productRepo.findById(id);
//        return categoryOpt.map(this::convertFindByIdDTO).orElse(null);
        if (productOpt.isPresent()) {
            return convertFindByIdDTO(productOpt.get());
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }


    @Override
    public String insert(ProductDTO productDTO) {
        if (productDTO.getTitle() == null) {
            return "error: Title is required";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        Product product = new Product();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(formattedDateTime);
        product.setTitle(productDTO.getTitle());
        product.setHandle(productDTO.getHandle());
        product.setBody_html(productDTO.getBody_html());
        product.setVendor(productDTO.getVendor());
        product.setProduct_type(productDTO.getProduct_type());
        product.setTags(productDTO.getTags());
        product.setStatus(productDTO.getStatus());
        product.setCreated_at(timestamp.toString());
        product.setUpdated_at(timestamp.toString());
        Product savedProduct = productRepo.save(product);

        if (productDTO.getImages() != null) {
            for (ImageDTO imageDTO : productDTO.getImages()) {
                Image image = new Image();
                if (imageDTO.getSrc() == null) {
                    return "error: Image url image is required";
                }
                image.setSrc(imageDTO.getSrc());
                image.setHeight(imageDTO.getHeight());
                image.setWidth(imageDTO.getWidth());
                image.setCreated_at(timestamp.toString());
                image.setUpdated_at(timestamp.toString());
                image.setProduct(savedProduct);
                imageRepo.save(image);
            }
        }
        return "Product saved with id: " + savedProduct.getId();

    }

    @Override
    public String update(Long id, ProductDTO productDTO) {
        Optional<Product> productOpt = productRepo.findById(id);
        if (productOpt.isEmpty()) {
            return "error: Product not found";
        }
        Product product = productOpt.get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(formattedDateTime);
        if(productDTO.getTitle() != null) {
            product.setTitle(productDTO.getTitle());
        }
        if (productDTO.getHandle() != null) {
            product.setHandle(productDTO.getHandle());
        }
        if (productDTO.getBody_html() != null) {
            product.setBody_html(productDTO.getBody_html());
        }
        product.setUpdated_at(timestamp.toString());
        Product updatedProduct = productRepo.save(product);

        if (productDTO.getImages() != null) {
            for (ImageDTO imageDTO : productDTO.getImages()) {
                Optional<Image> imageOpt = imageRepo.findById(imageDTO.getId());
                if (imageOpt.isEmpty()) {
                    return "error: Image not found";
                }
                Image image = imageOpt.get();
                if (imageDTO.getSrc() != null) {
                    image.setSrc(imageDTO.getSrc());
                }
                if (imageDTO.getHeight() != 0) {
                    image.setHeight(imageDTO.getHeight());
                }
                if (imageDTO.getWidth() != 0) {
                    image.setWidth(imageDTO.getWidth());
                }
                image.setUpdated_at(timestamp.toString());
                image.setProduct(updatedProduct);
                imageRepo.save(image);
            }
        }
        return "Category saved with id: " + updatedProduct.getId();
    }

    @Override
    public void delete(Product product) {
        productRepo.delete(product);
    }

//    @Override
//    public List<Image> findImagesByCategoryId(Long id) {
//        Optional<Category> category = categoryRepo.findById(id);
//        return category.map(Category::getImages).orElse(null);
//    }


    // get all categories
    @Override
    public List<ProductDTO> getAllProductDTO() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setHandle(product.getHandle());
        productDTO.setTitle(product.getTitle());
        productDTO.setBody_html(product.getBody_html());
        productDTO.setVendor(product.getVendor());
        productDTO.setProduct_type(product.getProduct_type());
        productDTO.setTags(product.getTags());
        productDTO.setStatus(product.getStatus());

        List<ImageDTO> imageDTOs = product.getImages().stream().map(image -> {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(image.getId());
            imageDTO.setSrc(image.getSrc());
            imageDTO.setHeight(image.getHeight());
            imageDTO.setWidth(image.getWidth());
            imageDTO.setCreated_at(image.getCreated_at());
            imageDTO.setUpdated_at(image.getUpdated_at());
            return imageDTO;
        }).collect(Collectors.toList());

        productDTO.setImages(imageDTOs);
        return productDTO;
    }
}
