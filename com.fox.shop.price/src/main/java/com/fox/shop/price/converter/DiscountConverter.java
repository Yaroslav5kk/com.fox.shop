package com.fox.shop.price.converter;

import com.fox.shop.price.entity.DiscountEntity;
import com.fox.shop.price.protocol.model.full.DiscountModel;
import com.fox.shop.price.protocol.model.request.DiscountOnCreateRequest;

public interface DiscountConverter {
    DiscountEntity requestToEntity(DiscountOnCreateRequest request);

    DiscountModel entityToModel(DiscountEntity entity);
}
