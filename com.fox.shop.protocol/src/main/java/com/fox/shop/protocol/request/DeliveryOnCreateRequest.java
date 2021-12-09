package com.fox.shop.protocol.request;

import com.fox.shop.protocol.type.DeliveryType;

public class DeliveryOnCreateRequest {
    private String name;
    private DeliveryType type;
    private long merchantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeliveryType getType() {
        return type;
    }

    public void setType(DeliveryType type) {
        this.type = type;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }
}
