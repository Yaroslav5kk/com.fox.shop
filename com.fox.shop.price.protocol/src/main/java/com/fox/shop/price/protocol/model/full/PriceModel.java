package com.fox.shop.price.protocol.model.full;

import com.fox.shop.price.protocol.types.CurrencyType;

import java.util.Date;
import java.util.List;

public class PriceModel {
    private long productId;
    private CurrencyType currency;
    private int price;
    private List<DiscountModel> discounts;
    private Date createdAt;
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

    public List<DiscountModel> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountModel> discounts) {
        this.discounts = discounts;
    }
}
