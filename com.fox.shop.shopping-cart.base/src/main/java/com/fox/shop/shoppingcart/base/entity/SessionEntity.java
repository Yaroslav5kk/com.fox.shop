package com.fox.shop.shoppingcart.base.entity;


import com.fox.shop.shoppingcart.base.entity.listener.SessionListener;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import com.fox.shop.shoppingcart.protocol.types.SessionStatusType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EntityListeners(SessionListener.class)
@Table(name = "session", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"user_id", "origin_type"})}
)
public class SessionEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_id")
    private long userId;
    private int total;
    @Enumerated(EnumType.STRING)
    private SessionStatusType status;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "origin_type")
    @Enumerated(EnumType.STRING)
    private SessionOriginType originType;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItemEntity> items = new HashSet<>();

    public Map<Long, CartItemEntity> getItemAsProductIdCartItem() {
        return items.stream()
                .collect(Collectors.toMap(CartItemEntity::getProductId, cartItemEntity -> cartItemEntity));
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public Set<CartItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<CartItemEntity> items) {
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SessionStatusType getStatus() {
        return status;
    }

    public void setStatus(SessionStatusType status) {
        this.status = status;
    }

    public SessionOriginType getOriginType() {
        return originType;
    }

    public void setOriginType(SessionOriginType originType) {
        this.originType = originType;
    }

}
