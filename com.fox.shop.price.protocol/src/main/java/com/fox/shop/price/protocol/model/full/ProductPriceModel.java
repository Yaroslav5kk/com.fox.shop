package com.fox.shop.price.protocol.model.full;

import com.fox.shop.price.protocol.types.CurrencyType;

public class ProductPriceModel {
    private long productId;
    private int price;
    private CurrencyType currency;
    private String priceToView;

    public ProductPriceModel() {
    }

    public ProductPriceModel(long productId, int price) {
        this.productId = productId;
        this.price = price;
    }

    public ProductPriceModel(long productId, int price, CurrencyType currency, String priceToView) {
        this.productId = productId;
        this.price = price;
        this.currency = currency;
        this.priceToView = priceToView;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceToView() {
        return priceToView;
    }

    public void setPriceToView(String priceToView) {
        this.priceToView = priceToView;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }
}
