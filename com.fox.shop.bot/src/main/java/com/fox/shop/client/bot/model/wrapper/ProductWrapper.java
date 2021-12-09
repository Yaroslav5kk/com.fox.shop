package com.fox.shop.client.bot.model.wrapper;


import com.fox.shop.protocol.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProductWrapper {

    private final ProductModel model;

    public ProductWrapper() {
        model = new ProductModel();
    }

    public ProductWrapper(final ProductModel model) {
        this.model = model;
    }

    public long getId() {
        return model.getId();
    }

    public void setId(long id) {
        model.setId(id);
    }

    public String getName() {
        return model.getName();
    }

    public void setName(String name) {
        model.setName(name);
    }

    public String getDescription() {
        return model.getDescription();
    }

    public void setDescription(String description) {
        model.setDescription(description);
    }

    public List<ImageWrapper> getImages() {
        return model.getImages().stream().
                map(ImageWrapper::new).
                collect(Collectors.toList());
    }

    public void setImages(List<ImageWrapper> images) {
        model.setImages(images.stream().
                map(ImageWrapper::getModel).
                collect(Collectors.toList()));
    }


    public ProductModel getModel() {
        return model;
    }
}
