package com.fox.shop.protocol.request;

import java.util.List;

public class ProductBalanceOnCreateRequest {
  private short balance;
  private List<Long> productIds;

  public short getBalance() {
    return balance;
  }

  public void setBalance(short balance) {
    this.balance = balance;
  }

  public List<Long> getProductIds() {
    return productIds;
  }

  public void setProductIds(List<Long> productIds) {
    this.productIds = productIds;
  }
}
