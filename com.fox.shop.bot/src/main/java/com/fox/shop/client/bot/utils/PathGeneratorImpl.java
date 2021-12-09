package com.fox.shop.client.bot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;

@Component
public class PathGeneratorImpl implements PathGenerator {
    private long counter = 1;

    @Override
    public String forProductImage(
            final Document document,
            final Integer userId
    ) {
        return document.getFileName();
        /*return new StringBuilder(imageProductPath).
                append(document.getFileId()).
                append(userId).
                append(extractFormat(document.getFileName())).
                toString();*/
    }

    @Override
    public String forShopImage(
            final Document document,
            final Integer userId
    ) {
        return new StringBuilder("kek").
                append(document.getFileName()).
                toString();
    }

    @Override
    public String extractFormat(final String input) {
        final String[] split = input.split("\\.");
        return split[split.length - 1];
    }
}
