package com.example.backend_commerce.services;

import com.example.backend_commerce.dto.CategoryDTO;
import com.example.backend_commerce.dto.ImageDTO;
import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.models.Image;
import com.example.backend_commerce.repositories.CategoryRepository;
import com.example.backend_commerce.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ImageRepository imageRepo;

    @Override
    public Optional<Category> findCategoryById(Long id) {
        if (categoryRepo.countById(id) == 0) {
            return Optional.empty();
        }

        return categoryRepo.findById(id);
    }

    @Override
    public CategoryDTO findCategoryDTOById(Long id) {
        Optional<Category> categoryOpt = categoryRepo.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setHandle(category.getHandle());
            categoryDTO.setTitle(category.getTitle());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setCreated_at(category.getCreated_at());
            categoryDTO.setUpdated_at(category.getUpdated_at());

            List<ImageDTO> imageDTOs = category.getImages().stream().map(image -> {
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setSrc(image.getSrc());
                imageDTO.setHeight(image.getHeight());
                imageDTO.setWidth(image.getWidth());
                imageDTO.setCreated_at(image.getCreated_at());
                imageDTO.setUpdated_at(image.getUpdated_at());
                return imageDTO;
            }).collect(Collectors.toList());

            categoryDTO.setImages(imageDTOs);

            return categoryDTO;
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }


    @Override
    public String insert(CategoryDTO categoryDTO) {
        if (categoryDTO.getTitle() == null) {
            return "error: Title is required";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        Category category = new Category();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(formattedDateTime);
        category.setTitle(categoryDTO.getTitle());
        category.setHandle(categoryDTO.getHandle());
        category.setDescription(categoryDTO.getDescription());
        category.setCreated_at(timestamp.toString());
        category.setUpdated_at(timestamp.toString());
        Category savedCategory = categoryRepo.save(category);

        if (categoryDTO.getImages() != null) {
            for (ImageDTO imageDTO : categoryDTO.getImages()) {
                Image image = new Image();
                if (imageDTO.getSrc() == null) {
                    return "error: Image url image is required";
                }
                image.setSrc(imageDTO.getSrc());
                image.setHeight(imageDTO.getHeight());
                image.setWidth(imageDTO.getWidth());
                image.setCreated_at(timestamp.toString());
                image.setUpdated_at(timestamp.toString());
                image.setCategory(savedCategory);
                imageRepo.save(image);
            }
        }
        return "Category saved with id: " + savedCategory.getId();

    }

    @Override
    public String update(Long id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOpt = categoryRepo.findById(id);
        if (categoryOpt.isEmpty()) {
            return "error: Category not found";
        }
        Category category = categoryOpt.get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(formattedDateTime);
        if (categoryDTO.getTitle() != null) {
            category.setTitle(categoryDTO.getTitle());
        }
        if (categoryDTO.getHandle() != null) {
            category.setHandle(categoryDTO.getHandle());
        }
        if (categoryDTO.getDescription() != null) {
            category.setDescription(categoryDTO.getDescription());
        }
        category.setUpdated_at(timestamp.toString());
        Category updatedCategory = categoryRepo.save(category);

        if (categoryDTO.getImages() != null) {
            for (ImageDTO imageDTO : categoryDTO.getImages()) {
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
                image.setCategory(updatedCategory);
                imageRepo.save(image);
            }
        }
        return "Category saved with id: " + updatedCategory.getId();
    }

    @Override
    public void delete(Category category) {
        categoryRepo.delete(category);
    }

//    @Override
//    public List<Image> findImagesByCategoryId(Long id) {
//        Optional<Category> category = categoryRepo.findById(id);
//        return category.map(Category::getImages).orElse(null);
//    }


    // get all categories
    @Override
    public List<CategoryDTO> getALLCategoryDTO() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setHandle(category.getHandle());
        categoryDTO.setTitle(category.getTitle());
        categoryDTO.setDescription(category.getDescription());

        List<ImageDTO> imageDTOs = category.getImages().stream().map(image -> {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(image.getId());
            imageDTO.setSrc(image.getSrc());
            imageDTO.setHeight(image.getHeight());
            imageDTO.setWidth(image.getWidth());
            imageDTO.setCreated_at(image.getCreated_at());
            imageDTO.setUpdated_at(image.getUpdated_at());
            return imageDTO;
        }).collect(Collectors.toList());

        categoryDTO.setImages(imageDTOs);
        return categoryDTO;
    }
}
