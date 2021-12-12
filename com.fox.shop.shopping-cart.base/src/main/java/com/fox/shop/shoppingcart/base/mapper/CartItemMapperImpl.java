package com.fox.shop.shoppingcart.base.mapper;

import com.fox.shop.shoppingcart.base.entity.CartItemEntity;
import com.fox.shop.shoppingcart.base.mapper.i.CartItemMapper;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemEntity fromRequestToEntity(final CartItemOnCreateRequest request) {
        final CartItemEntity result = new CartItemEntity();
        result.setProductId(request.getProductId());
        result.setQuantity(request.getQuantity());
        return result;
    }

    @Override
    public FullCartItemModel entityToModel(final CartItemEntity entity) {
        final FullCartItemModel result = new FullCartItemModel();
        result.setId(entity.getId());
        result.setProductId(entity.getProductId());
        result.setQuantity(entity.getQuantity());
        result.setProductMainImageId(entity.getProductMainImageId());
        result.setProductName(entity.getProductName());
        return result;
    }

}
