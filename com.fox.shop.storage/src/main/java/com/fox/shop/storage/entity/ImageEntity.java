package com.fox.shop.storage.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "image")
public class ImageEntity implements FatherResource{
  private String id = UUID.randomUUID().toString();
  private String telegramId;
}
