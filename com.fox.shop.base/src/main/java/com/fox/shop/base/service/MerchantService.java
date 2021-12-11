package com.fox.shop.base.service;

import com.fox.shop.protocol.response.GeneralResponse;

public interface MerchantService {
  GeneralResponse getMerchantIdByProductId(long productId);
}
