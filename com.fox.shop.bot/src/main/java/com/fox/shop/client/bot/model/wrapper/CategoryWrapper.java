package com.fox.shop.client.bot.model.wrapper;

import com.fox.shop.protocol.CategoryModel;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryWrapper {
    private final CategoryModel model;

    public CategoryWrapper() {
        model = new CategoryModel();
    }

    public CategoryWrapper(final CategoryModel model) {
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

    public List<ProductWrapper> getItems() {
        return model.getItems().stream().map(ProductWrapper::new).collect(Collectors.toList());
    }

    public void setItems(List<ProductWrapper> items) {
        model.setItems(items.stream().map(ProductWrapper::getModel).collect(Collectors.toList()));
    }

    public CategoryModel getModel() {
        return model;
    }
}
















