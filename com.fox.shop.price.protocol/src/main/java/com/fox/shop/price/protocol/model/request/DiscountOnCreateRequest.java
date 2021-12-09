package com.fox.shop.price.protocol.model.request;

import java.math.BigDecimal;

public class DiscountOnCreateRequest {
    private String name;
    private String description;
    private short discountPercent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(short discountPercent) {
        this.discountPercent = discountPercent;
    }
}
