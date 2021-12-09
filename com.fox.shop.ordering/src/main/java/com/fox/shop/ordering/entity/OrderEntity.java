package com.fox.shop.ordering.entity;

import com.fox.shop.ordering.protocol.types.OrderOriginType;
import com.fox.shop.ordering.protocol.types.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "order_details")
public class OrderEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    @Field(name = "telegram_username")
    private String telegramUsername;
    @Field(name = "user_id")
    private long userId;
    @Field(name = "merchant_id")
    private long merchantId;
    @Field(name = "total_price")
    private int totalPrice;
    @Field(name = "created_at")
    private Date createdAt;
    @Field(name = "updated_at")
    private Date updatedAt;
    private String phone;
    private String address;
    private String firstname;
    private String lastname;
    private OrderStatus status;
    @Field(name = "origin_type")
    private OrderOriginType originType;
    private List<OrderItemEntity> items = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderOriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OrderOriginType originType) {
        this.originType = originType;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }
}


















