package com.fox.shop.price.protocol.model.request;

import com.fox.shop.price.protocol.types.CurrencyType;

import java.util.List;

public class PriceOnCreateRequest {
    private long productId;
    private CurrencyType currency;
    private int price;
    private List<DiscountOnCreateRequest> discounts;

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

    public List<DiscountOnCreateRequest> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountOnCreateRequest> discounts) {
        this.discounts = discounts;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
