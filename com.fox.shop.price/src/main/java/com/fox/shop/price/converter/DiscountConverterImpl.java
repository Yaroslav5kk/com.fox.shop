package com.fox.shop.price.converter;

import com.fox.shop.price.entity.DiscountEntity;
import com.fox.shop.price.protocol.model.full.DiscountModel;
import com.fox.shop.price.protocol.model.request.DiscountOnCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class DiscountConverterImpl implements DiscountConverter {

    @Override
    public DiscountEntity requestToEntity(final DiscountOnCreateRequest request) {
        final DiscountEntity result = new DiscountEntity();
        result.setName(request.getName());
        result.setDescription(request.getDescription());
        result.setDiscountPercent(request.getDiscountPercent());
        return result;
    }

    @Override
    public DiscountModel entityToModel(final DiscountEntity entity) {
        final DiscountModel result = new DiscountModel();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        result.setDiscountPercent(entity.getDiscountPercent());
        result.setCreatedAt(entity.getCreatedAt());
        result.setModifiedAt(entity.getModifiedAt());
        return result;
    }
}
