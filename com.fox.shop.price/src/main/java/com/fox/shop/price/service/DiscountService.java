package com.fox.shop.price.service;

import com.fox.shop.price.protocol.model.full.DiscountModel;
import com.fox.shop.price.protocol.model.request.DiscountOnCreateRequest;

public interface DiscountService {
    DiscountModel save(DiscountOnCreateRequest request);
}
