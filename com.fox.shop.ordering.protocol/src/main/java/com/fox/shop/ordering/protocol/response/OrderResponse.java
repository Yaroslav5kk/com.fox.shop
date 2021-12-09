package com.fox.shop.ordering.protocol.response;

import com.fox.shop.ordering.protocol.types.OrderOriginType;
import com.fox.shop.ordering.protocol.types.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderResponse {
    private String id;
    private long userId;
    private BigDecimal totalPrice;
    private Date createdAt;
    private Date updatedAt;
    private String phone;
    private String name;
    private OrderStatus status;
    private OrderOriginType originType;
    private List<Long> items;

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

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderOriginType getOriginType() {
        return originType;
    }

    public void setOriginType(OrderOriginType originType) {
        this.originType = originType;
    }
}
