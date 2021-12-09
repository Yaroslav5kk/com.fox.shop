package com.fox.shop.protocol.request;

import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.ProductModel;

import java.util.List;

public class MerchantOnCreateRequest {
    private long id;
    private String name;
    private long deliveryId;
    private List<Long> productIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}













