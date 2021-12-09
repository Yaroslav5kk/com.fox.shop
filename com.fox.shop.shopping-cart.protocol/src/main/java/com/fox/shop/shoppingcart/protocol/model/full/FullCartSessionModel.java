package com.fox.shop.shoppingcart.protocol.model.full;

import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import com.fox.shop.shoppingcart.protocol.types.SessionStatusType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullCartSessionModel {
    private long id;
    private long userId;
    private SessionStatusType status;
    private Date createdAt;
    private Date updatedAt;
    private SessionOriginType originType;
    private List<FullCartItemModel> items = new ArrayList<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public List<FullCartItemModel> getItems() {
        return items;
    }

    public void setItems(List<FullCartItemModel> items) {
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
