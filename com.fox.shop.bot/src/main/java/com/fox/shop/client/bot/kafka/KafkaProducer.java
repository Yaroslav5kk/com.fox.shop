package com.fox.shop.client.bot.kafka;

import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;

public interface KafkaProducer {

    FullCartSessionModel addToCart(AddToCartRequest dto);

    void makeOrder(OrderOnCreateRequest request);
}
