package com.fox.shop.price.converter;

import com.fox.shop.price.entity.PriceEntity;
import com.fox.shop.price.protocol.model.full.PriceModel;
import com.fox.shop.price.protocol.model.request.PriceOnCreateRequest;

public interface PriceConverter {
    PriceEntity requestToEntity(PriceOnCreateRequest request);

    PriceModel entityToModel(PriceEntity entity);
}
