package com.fox.shop.ordering.protocol.model;

public class OrderItemModel {
    private long id;
    private long productId;
    private int priceAtOne;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceAtOne() {
        return priceAtOne;
    }

    public void setPriceAtOne(int priceAtOne) {
        this.priceAtOne = priceAtOne;
    }
}
