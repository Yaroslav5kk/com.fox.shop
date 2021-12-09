package com.fox.shop.client.bot.kafka;

import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;

public interface KafkaProducer {

    void addToCart(AddToCartRequest dto);

    void makeOrder(OrderOnCreateRequest request);
}
