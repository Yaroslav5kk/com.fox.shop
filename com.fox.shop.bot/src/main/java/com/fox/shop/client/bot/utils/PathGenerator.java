package com.fox.shop.client.bot.utils;

import org.telegram.telegrambots.meta.api.objects.Document;

public interface PathGenerator {

    String forProductImage(
            Document document,
            Integer userId
    );

    String forShopImage(
            Document document,
            Integer userId
    );

    String extractFormat(String input);
}
