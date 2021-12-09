package com.fox.shop.ordering.api.client.i;

import com.fox.shop.price.protocol.model.full.ProductPriceModel;

import java.util.List;

public interface PriceApiClient {
    /*--------------------------------------------- product ----------------------------------------------------*/
    List<ProductPriceModel> allProductPriceByIds(List<Long> ids);
}
