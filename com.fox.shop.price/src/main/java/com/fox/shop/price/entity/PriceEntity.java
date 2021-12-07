package com.fox.shop.price.entity;

import com.fox.shop.price.protocol.types.CurrencyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "price")
public class PriceEntity {
    @Id
    @Field(name = "product_id")
    private long productId;
    private CurrencyType currency;
    private int price;
    @DBRef
    private List<DiscountEntity> discounts = new ArrayList<>();
    @Field(name = "create_at")
    private Date createdAt;
    @Field(name = "modified_at")
    private Date modifiedAt;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<DiscountEntity> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountEntity> discounts) {
        this.discounts = discounts;
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
