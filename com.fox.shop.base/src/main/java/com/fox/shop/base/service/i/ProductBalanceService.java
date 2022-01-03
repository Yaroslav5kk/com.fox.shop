package com.fox.shop.base.service.i;

import com.fox.shop.protocol.ProductBalanceModel;
import com.fox.shop.protocol.request.ProductBalanceOnCreateRequest;

public interface ProductBalanceService {
  ProductBalanceModel save(ProductBalanceOnCreateRequest request);
}
