package com.fox.shop.ordering.service;

import com.fox.shop.ordering.api.client.i.PriceApiClient;
import com.fox.shop.ordering.entity.OrderItemEntity;
import com.fox.shop.ordering.service.i.CalculateService;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    final Map<Long, Integer> productIdQuantity = items.stream().collect(Collectors
            .toMap(OrderItemEntity::getProductId, OrderItemEntity::getQuantity)
    );
    final List<ProductPriceModel> prices = priceApiClient.allProductPriceByIds(
            new ArrayList<>(productIdQuantity.keySet())
    );
    int result = 0;
    for (var itPrice : prices) {
      result += (itPrice.getPrice() * productIdQuantity.get(itPrice.getProductId()));
    }
    return result;
  }
}
