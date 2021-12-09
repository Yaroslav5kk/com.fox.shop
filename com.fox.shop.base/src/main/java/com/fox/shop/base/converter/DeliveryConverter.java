package com.fox.shop.base.converter;

import com.fox.shop.base.entity.DeliveryEntity;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.request.DeliveryOnCreateRequest;

public class DeliveryConverter {


    public static DeliveryEntity fromRequestOnCreateToEntity(final DeliveryOnCreateRequest request) {
        final DeliveryEntity result = new DeliveryEntity();
        result.setName(request.getName());
        result.setType(request.getType());
        return result;
    }

    public static DeliveryModel fromEntityToModel(final DeliveryEntity entity) {
        final DeliveryModel result = new DeliveryModel();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setType(entity.getType());
        return result;
    }

    public static DeliveryEntity fromModelToEntity(final DeliveryModel model) {
        final DeliveryEntity result = new DeliveryEntity();
        result.setId(model.getId());
        result.setName(model.getName());
        result.setType(model.getType());
        return result;
    }
}
