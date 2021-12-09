package com.fox.shop.base.converter;

import com.fox.shop.base.entity.CategoryEntity;
import com.fox.shop.protocol.CategoryModel;
import com.fox.shop.protocol.request.CategoryOnCreateRequest;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

public class CategoryConverter {


    public static CategoryEntity fromRequestOnCreateToEntity(final CategoryOnCreateRequest request) {
        final CategoryEntity result = new CategoryEntity();
        result.setName(request.getName());
        result.setDescription(request.getDescription());
        return result;
    }

    public static CategoryModel fromEntity(final CategoryEntity entity) {
        final CategoryModel result = new CategoryModel();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        if (entity.getImage() != null)
            result.setImage(ImageConverter.fromEntity(entity.getImage()));
        if (!CollectionUtils.isEmpty(entity.getProducts()))
            result.setItems(entity.getProducts().stream().
                    map(ProductConverter::fromEntity).collect(Collectors.toList()));
        return result;
    }

    public static CategoryEntity toEntity(final CategoryModel model) {
        final CategoryEntity result = new CategoryEntity();
        result.setId(model.getId());
        result.setName(model.getName());
        result.setDescription(model.getDescription());
        result.setImage(ImageConverter.toEntity(model.getImage()));
        if (!CollectionUtils.isEmpty(model.getItems()))
            result.setProducts(model.getItems().stream().
                    map(ProductConverter::toEntity).collect(Collectors.toList()));
        return result;
    }
}
