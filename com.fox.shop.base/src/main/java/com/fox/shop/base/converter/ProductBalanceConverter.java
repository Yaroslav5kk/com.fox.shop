package com.fox.shop.base.converter;

import com.fox.shop.base.entity.ProductBalanceEntity;
import com.fox.shop.protocol.ProductBalanceModel;
import com.fox.shop.protocol.request.ProductBalanceOnCreateRequest;

public class ProductBalanceConverter {


  public static ProductBalanceEntity fromRequestOnCreateToEntity(final ProductBalanceOnCreateRequest request) {
    final ProductBalanceEntity result = new ProductBalanceEntity();
    result.setBalance(request.getBalance());
    return result;
  }

  public static ProductBalanceModel entityToModel(final ProductBalanceEntity entity) {
    final ProductBalanceModel result = new ProductBalanceModel();
    result.setBalance(entity.getBalance());
    return result;
  }
}
