package com.fox.shop.ordering.converter;

import com.fox.shop.ordering.converter.i.OrderItemConverter;
import com.fox.shop.ordering.entity.OrderItemEntity;
import com.fox.shop.ordering.protocol.model.OrderItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverterImpl implements OrderItemConverter {

    @Override
    public OrderItemEntity fromOrderItemToEntity(final OrderItemModel model) {
        final OrderItemEntity result = new OrderItemEntity();
        result.setProductId(model.getProductId());
        result.setPriceAtOne(model.getPriceAtOne());
        result.setQuantity(model.getQuantity());
        return result;
    }

    @Override
    public OrderItemEntity fromFullCartItemToEntity(
            final FullCartItemModel model,
            final int priceAtOne
    ) {
        final OrderItemEntity result = new OrderItemEntity();
        result.setProductId(model.getProductId());
        result.setPriceAtOne(priceAtOne);
        result.setQuantity(model.getQuantity());
        return result;
    }

    @Override
    public OrderItemModel fromEntityToModel(final OrderItemEntity entity) {
        final OrderItemModel result = new OrderItemModel();
        result.setId(entity.getId());
        result.setProductId(entity.getProductId());
        result.setPriceAtOne(entity.getPriceAtOne());
        result.setQuantity(result.getQuantity());
        return result;
    }

}












