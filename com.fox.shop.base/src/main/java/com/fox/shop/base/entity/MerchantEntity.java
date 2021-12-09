package com.fox.shop.base.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "merchant")
public class MerchantEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products = new ArrayList<>();
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryEntity> deliveries = new ArrayList<>();

    public void addProduct(final ProductEntity product) {
        products.add(product);
        product.setMerchant(this);
    }

    public void removeProduct(final ProductEntity product) {
        products.remove(product);
        product.setMerchant(null);
    }

    public void addDelivery(final DeliveryEntity delivery) {
        deliveries.add(delivery);
        delivery.setMerchant(this);
    }

    public void removeDelivery(final DeliveryEntity delivery) {
        deliveries.remove(delivery);
        delivery.setMerchant(null);
    }

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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public List<DeliveryEntity> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryEntity> deliveries) {
        this.deliveries = deliveries;
    }
}
