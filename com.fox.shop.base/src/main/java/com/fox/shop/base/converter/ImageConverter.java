package com.fox.shop.base.converter;

import com.fox.shop.base.entity.ImageEntity;
import com.fox.shop.protocol.ImageModel;
import com.fox.shop.protocol.request.ImageOnCreateRequest;

public class ImageConverter {

    public static ImageEntity fromRequestOnCreateToEntity(final ImageOnCreateRequest request) {
        final ImageEntity result = new ImageEntity();
        result.setUrl(request.getUrl());
        result.setName(request.getName());
        result.setProvider(request.getProvider());
        result.setProviderType(request.getProviderType());
        return result;
    }

    public static ImageModel fromEntity(final ImageEntity entity) {
        final ImageModel result = new ImageModel();
        result.setId(entity.getId());
        result.setUrl(entity.getUrl());
        result.setName(entity.getName());
        result.setProvider(entity.getProvider());
        result.setProviderType(entity.getProviderType());
        return result;
    }

    public static ImageEntity toEntity(final ImageModel model) {
        final ImageEntity result = new ImageEntity();
        result.setId(model.getId());
        result.setUrl(model.getUrl());
        result.setName(model.getName());
        result.setProvider(model.getProvider());
        result.setProviderType(model.getProviderType());
        return result;
    }
}
