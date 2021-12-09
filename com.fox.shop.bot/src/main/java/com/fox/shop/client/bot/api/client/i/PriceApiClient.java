package com.fox.shop.client.bot.api.client.i;

import com.fox.shop.price.protocol.model.full.ProductPriceModel;

public interface PriceApiClient {
    ProductPriceModel getByProductId(long productId,int quantity);
}
