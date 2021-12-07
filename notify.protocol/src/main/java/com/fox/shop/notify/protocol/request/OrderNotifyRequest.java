package com.fox.shop.notify.protocol.request;

public class OrderNotifyRequest {
  private long merchantId;
  private long orderId;
  private String firstName;
  private String lastName;
  private String telegramUsername;
  private String phone;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTelegramUsername() {
    return telegramUsername;
  }

  public void setTelegramUsername(String telegramUsername) {
    this.telegramUsername = telegramUsername;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public long getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(long merchantId) {
    this.merchantId = merchantId;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }
}
