package com.fox.shop.protocol.request;

import com.fox.shop.protocol.type.ProductGroupType;

public class GroupOnCreateRequest {
    private String name;
    private String description;
    private ProductGroupType type;
    private String mainImageStorageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductGroupType getType() {
        return type;
    }

    public void setType(ProductGroupType type) {
        this.type = type;
    }

    public String getMainImageStorageId() {
        return mainImageStorageId;
    }

    public void setMainImageStorageId(String mainImageStorageId) {
        this.mainImageStorageId = mainImageStorageId;
    }
}
