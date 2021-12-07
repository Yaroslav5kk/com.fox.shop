package com.fox.shop.notify.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MerchantInfoEntity {
  @Id
  private long merchantIdOnBase;

}
