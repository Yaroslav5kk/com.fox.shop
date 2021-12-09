package com.fox.shop.shoppingcart.base.kafka;

import com.fox.shop.shoppingcart.base.service.i.SessionService;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.GeneralRequest;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerImpl implements KafkaConsumer {

    private final SessionService sessionService;

    public KafkaConsumerImpl(
            final SessionService sessionService
    ) {
        this.sessionService = sessionService;
    }

    @Override
    //@KafkaListener(id = "${kafka.group-id}", topics = {"${kafka.topic.add-to-cart}"})
    public void addToCart(final GeneralRequest<AddToCartRequest> dto) {
        sessionService.addToCart(dto.getBody());
    }

}
