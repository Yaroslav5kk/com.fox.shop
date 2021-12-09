package com.fox.shop.ordering.service;

import com.fox.shop.ordering.api.client.i.PriceApiClient;
import com.fox.shop.ordering.entity.OrderItemEntity;
import com.fox.shop.ordering.service.i.CalculateService;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final PriceApiClient priceApiClient;

    public CalculateServiceImpl(
            final PriceApiClient priceApiClient
    ) {
        this.priceApiClient = priceApiClient;
    }

    @Override
    public int totalPriceByOrderItems(final List<OrderItemEntity> items) {
        final List<Long> productIds = items.stream().map(OrderItemEntity::getProductId).collect(Collectors.toList());
        final List<ProductPriceModel> prices = priceApiClient.allProductPriceByIds(productIds);
        int result = 0;
        for (var itPrice : prices) {
            result +=itPrice.getPrice();
        }
        return result;
    }
}
