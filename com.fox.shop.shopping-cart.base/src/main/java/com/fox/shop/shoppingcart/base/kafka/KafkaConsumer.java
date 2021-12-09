package com.fox.shop.shoppingcart.base.kafka;

import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.GeneralRequest;

public interface KafkaConsumer {

    void addToCart(GeneralRequest<AddToCartRequest> dto);
}
