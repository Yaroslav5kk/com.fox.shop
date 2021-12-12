package com.fox.shop.shoppingcart.protocol.model.full;

import java.util.Date;

public class FullCartItemModel {
    private long id;
    private long productId;
    @Deprecated
    private long productMainImageId;
    private int quantity;
    private String productName;
    private Date createdAt;
    private Date updatedAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
