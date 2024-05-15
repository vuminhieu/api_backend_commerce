package com.example.backend_commerce.dto;

import java.util.List;

public class CategoryDTO {

    private long id;
    private String handle;
    private String title;
    private String description;
    private List<ImageDTO> images;
    private String created_at;
    private String updated_at;

    public CategoryDTO(long id, String handle, String title, String description, List<ImageDTO> images, String created_at, String updated_at) {
        this.id = id;
        this.handle = handle;
        this.title = title;
        this.description = description;
        this.images = images;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public CategoryDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
