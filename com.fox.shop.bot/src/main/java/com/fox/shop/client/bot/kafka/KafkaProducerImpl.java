package com.fox.shop.client.bot.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.client.i.OrderingApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.GeneralRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerImpl implements KafkaProducer {

    @Value("${kafka.topic.add-to-cart}")
    private String addToCartTopic;
    @Value("${kafka.topic.make-order}")
    private String makeOrderTopic;

    private final KafkaTemplate<Long, GeneralRequest> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final ShoppingCartApiClient shoppingCartApiClient;
    private final OrderingApiClient orderingApiClient;

    public KafkaProducerImpl(
            final KafkaTemplate<Long, GeneralRequest> kafkaTemplate,
            final ObjectMapper objectMapper,
            final ShoppingCartApiClient shoppingCartApiClient,
            final OrderingApiClient orderingApiClient
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.shoppingCartApiClient = shoppingCartApiClient;
        this.orderingApiClient = orderingApiClient;
    }

    @Override
    public FullCartSessionModel addToCart(final AddToCartRequest dto) {
        return shoppingCartApiClient.addToCart(dto);
        //kafkaTemplate.send(addToCartTopic, dto);
    }

    @Override
    public void makeOrder(final OrderOnCreateRequest request) {
        //billingApiClient.makeOrder(request);
        //kafkaTemplate.send(makeOrderTopic, dto);
    }


}

















