package com.example.backend_commerce.dto;

import lombok.Data;

@Data
public class ImageDTO {

    private long id;
    private Integer position;
    private double width;
    private double height;
    private String src;
    private String created_at;
    private String updated_at;

    public ImageDTO() {
    }

    public ImageDTO(long id, Integer position, double width, double height, String src, String created_at, String updated_at) {
        this.id = id;
        this.position = position;
        this.width = width;
        this.height = height;
        this.src = src;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getSrc() {
        return src;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
