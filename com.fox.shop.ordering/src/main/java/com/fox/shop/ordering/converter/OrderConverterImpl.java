package com.fox.shop.ordering.converter;

import com.fox.shop.notify.protocol.OrderItemNotifyModel;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.ordering.converter.i.OrderConverter;
import com.fox.shop.ordering.converter.i.OrderItemConverter;
import com.fox.shop.ordering.entity.OrderEntity;
import com.fox.shop.ordering.entity.OrderItemEntity;
import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderConverterImpl implements OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderConverterImpl(
            final OrderItemConverter orderItemConverter
    ) {
        this.orderItemConverter = orderItemConverter;
    }

    @Override
    public OrderEntity fromOnCreateRequestToEntity(final OrderOnCreateRequest request) {
        final OrderEntity result = new OrderEntity();
        result.setTelegramUsername(result.getTelegramUsername());
        result.setFirstname(request.getFirstname());
        result.setLastname(request.getLastname());
        result.setPhone(request.getPhone());
        result.setOriginType(request.getOriginType());
        result.setAddress(request.getAddress());
        return result;
    }

    @Override
    public OrderModel fromEntityToModel(final OrderEntity entity) {
        final OrderModel result = new OrderModel();
        result.setId(entity.getId());
        result.setTelegramUsername(entity.getTelegramUsername());
        result.setMerchantId(entity.getMerchantId());
        result.setStatus(entity.getStatus());
        result.setCreatedAt(entity.getCreatedAt());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setPhone(entity.getPhone());
        result.setUserId(entity.getUserId());
        result.setTotalPrice(entity.getTotalPrice());
        result.setOriginType(entity.getOriginType());
        result.setFirstname(entity.getFirstname());
        result.setLastname(entity.getLastname());
        result.setAddress(entity.getAddress());
        result.setItems(entity.getItems().stream().
                map(orderItemConverter::fromEntityToModel).
                collect(Collectors.toList())
        );
        return result;
    }

    @Override
    public OrderNotifyRequest fromEntityToOrderNotifyRequest(
            final OrderEntity entity,
            final Map<Long, FullCartItemModel> productIdCartItem
    ) {
        final OrderNotifyRequest result = new OrderNotifyRequest();
        result.setOrderId(entity.getId());
        result.setTelegramUsername(entity.getTelegramUsername());
        result.setMerchantId(entity.getMerchantId());
        result.setPhone(entity.getPhone());
        result.setFirstName(entity.getFirstname());
        result.setLastName(entity.getLastname());
        result.setItems(entity.getItems()
                .stream()
                .map(orderItemEntity -> buildOrderItemNotifyModel(orderItemEntity, productIdCartItem.get(orderItemEntity.getProductId())))
                .collect(Collectors.toList())
        );
        return result;
    }

    private OrderItemNotifyModel buildOrderItemNotifyModel(
            final OrderItemEntity orderItemEntity,
            final FullCartItemModel cartItemModel
    ) {
        final OrderItemNotifyModel result = new OrderItemNotifyModel();
        result.setProductId(orderItemEntity.getProductId());
        result.setPriceAtOne(orderItemEntity.getPriceAtOne());
        result.setQuantity(orderItemEntity.getQuantity());
        result.setProductName(cartItemModel.getProductName());
        result.setProductMainImageId(cartItemModel.getProductMainImageId());
        return result;
    }
}
