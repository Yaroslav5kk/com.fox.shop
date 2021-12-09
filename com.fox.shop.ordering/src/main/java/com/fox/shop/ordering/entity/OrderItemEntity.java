package com.fox.shop.ordering.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderItemEntity {
    private long id;
    @Field(name = "product_Id")
    private long productId;
    @Field(name = "price_at_one")
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
