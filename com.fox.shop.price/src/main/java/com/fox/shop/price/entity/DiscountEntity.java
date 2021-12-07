package com.fox.shop.price.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document(collection = "discount")
public class DiscountEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    @Field(name = "discount_percent")
    private short discountPercent;
    @Field(name = "create_at")
    private Date createdAt;
    @Field(name = "modified_at")
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
