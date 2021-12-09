package com.fox.shop.ordering.service.i;

import com.fox.shop.ordering.entity.OrderItemEntity;

import java.util.List;

public interface CalculateService {
    int totalPriceByOrderItems(List<OrderItemEntity> items);
}
