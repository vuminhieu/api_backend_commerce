package com.example.backend_commerce.services;

import com.example.backend_commerce.models.Category;
import com.example.backend_commerce.models.Image;
import com.example.backend_commerce.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepo;

    @Override
    public Optional<Image> findImageById(Long id) {
        if (imageRepo.countById(id) == 0) {
            return Optional.empty();
        }
        return imageRepo.findById(id);
    }

    @Override
    public List<Image> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public String insert(Image image) {
        if (image.getSrc() == null) {
            return "error: Title is required";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);

        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(formattedDateTime);


        image.setCreated_at(timestamp.toString());
        image.setUpdated_at(timestamp.toString());
        Image savedImage = imageRepo.save(image);
        return "Category saved with id: " + savedImage.getId();
    }

    @Override
    public Image update(Image image) {
        return null;
    }

    @Override
    public void delete(Image image) {
        imageRepo.delete(image);
    }
}
