package com.fox.shop.base.converter;

import com.fox.shop.base.entity.ProductGroupEntity;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.request.GroupOnCreateRequest;

public class ProductGroupConverter {

    public static ProductGroupEntity fromRequestToEntity(final GroupOnCreateRequest request) {
        final ProductGroupEntity result = new ProductGroupEntity();
        result.setName(request.getName());
        result.setDescription(request.getDescription());
        result.setType(request.getType());
        return result;
    }

    public static ProductGroupModel fromEntity(final ProductGroupEntity entity) {
        final ProductGroupModel result = new ProductGroupModel();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        result.setImage(ImageConverter.fromEntity(entity.getMainImage()));
        result.setType(entity.getType());
        return result;
    }

    public static ProductGroupEntity toEntity(final ProductGroupModel model) {
        final ProductGroupEntity result = new ProductGroupEntity();
        result.setName(model.getName());
        result.setDescription(model.getDescription());
        result.setMainImage(ImageConverter.toEntity(model.getImage()));
        result.setType(model.getType());
        return result;
    }

}
