package com.fox.shop.base.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @JoinColumn(name = "image_id")
    @OneToOne
    private ImageEntity image;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(final ProductEntity productEntity) {
        products.add(productEntity);
        productEntity.setCategory(this);
    }

    public void removeProduct(final ProductEntity productEntity) {
        products.remove(productEntity);
        productEntity.setCategory(null);
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> items) {
        this.products = items;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }
}
