package com.fox.shop.price.service;

import com.fox.shop.price.protocol.model.full.PriceModel;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import com.fox.shop.price.protocol.model.request.PriceOnCreateRequest;

import java.util.List;

public interface PriceService {
    PriceModel save(PriceOnCreateRequest request);

    ProductPriceModel getByProductId(long productId, int quantity);

    List<ProductPriceModel> getByProductIds(
            List<Long> productIds
    );
}
