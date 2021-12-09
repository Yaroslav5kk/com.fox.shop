package com.fox.shop.base.entity;

import com.fox.shop.protocol.type.DeliveryType;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
public class DeliveryEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private DeliveryType type;
    @ManyToOne
    private MerchantEntity merchant;

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

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }
}
