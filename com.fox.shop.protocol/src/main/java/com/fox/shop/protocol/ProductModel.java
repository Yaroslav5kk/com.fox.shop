package com.fox.shop.protocol;

import java.util.List;

public class ProductModel {
    private long id;
    private String name;
    private String description;
    private ImageModel mainImage;
    private List<ImageModel> images;
    private MerchantModel merchant;

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

    @Override
    public String toString() {
        return "KitchenItemModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public ImageModel getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageModel mainImage) {
        this.mainImage = mainImage;
    }

    public MerchantModel getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModel merchant) {
        this.merchant = merchant;
    }
}
