package com.fox.shop.client.bot.entity;

import com.fox.shop.protocol.type.ImageProviderType;
import org.springframework.data.mongodb.core.mapping.Field;

public class ImageEntity {
    private String url;
    private String name;
    private String provider;
    @Field(name = "provider_type")
    private ImageProviderType providerType;
}
