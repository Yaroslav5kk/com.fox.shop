package com.fox.shop.protocol;

import com.fox.shop.protocol.type.ProductGroupType;

import java.util.ArrayList;
import java.util.List;

public class ProductGroupModel {
    private long id;
    private String name;
    private String description;
    private ProductGroupType type;
    private ImageModel image;
    private List<ProductModel> products = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    public ProductGroupType getType() {
        return type;
    }

    public void setType(ProductGroupType type) {
        this.type = type;
    }
}
