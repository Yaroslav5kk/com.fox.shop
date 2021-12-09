package com.fox.shop.protocol;

import com.fox.shop.protocol.type.DeliveryType;

public class DeliveryModel {
    private long id;
    private String name;
    private DeliveryType type;

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

    public DeliveryType getType() {
        return type;
    }

    public void setType(DeliveryType type) {
        this.type = type;
    }
}
