package com.fox.shop.base.entity;

import com.fox.shop.protocol.type.ProductGroupType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
public class ProductGroupEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "main_image_id")
    private ImageEntity mainImage;
    @Enumerated(EnumType.STRING)
    private ProductGroupType type;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ProductEntity> products = new ArrayList<>();

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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageEntity getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageEntity mainImage) {
        this.mainImage = mainImage;
    }

    public ProductGroupType getType() {
        return type;
    }

    public void setType(ProductGroupType type) {
        this.type = type;
    }
}