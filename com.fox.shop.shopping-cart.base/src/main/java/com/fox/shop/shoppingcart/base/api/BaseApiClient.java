package com.fox.shop.shoppingcart.base.api;

import com.fox.shop.protocol.ProductModel;

public interface BaseApiClient {
/*
    ProductPriceModel productPriceById(long productId);
*/

    ProductModel productById(long productId);

}
