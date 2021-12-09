package com.fox.shop.notify.protocol;

public class OrderItemNotifyModel {
    private long productId;
    private int priceAtOne;
    private int quantity;
    private String productName;
    private long productMainImageId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getPriceAtOne() {
        return priceAtOne;
    }

    public void setPriceAtOne(int priceAtOne) {
        this.priceAtOne = priceAtOne;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductMainImageId() {
        return productMainImageId;
    }

    public void setProductMainImageId(long productMainImageId) {
        this.productMainImageId = productMainImageId;
    }
}
