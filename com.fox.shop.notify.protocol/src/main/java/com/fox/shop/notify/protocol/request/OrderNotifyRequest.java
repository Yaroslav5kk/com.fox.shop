package com.fox.shop.notify.protocol.request;

import com.fox.shop.notify.protocol.OrderItemNotifyModel;

import java.util.List;

public class OrderNotifyRequest {
    private long merchantId;
    private String orderId;
    private String firstName;
    private String lastName;
    private String telegramUsername;
    private String phone;
    private int totalPrice;
    private List<OrderItemNotifyModel> items;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemNotifyModel> getItems() {
        return items;
    }

    public void setItems(List<OrderItemNotifyModel> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
