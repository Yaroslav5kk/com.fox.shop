package com.fox.shop.price.protocol.model.full;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountModel {
    private String id;
    private String name;
    private String description;
    private short discountPercent;
    private Date createdAt;
    private Date modifiedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
