package com.fox.shop.protocol.request;

import com.fox.shop.protocol.type.ProductGroupType;

public class GroupOnCreateRequest {
    private String name;
    private String description;
    private ProductGroupType type;
    private long imageId;

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

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public ProductGroupType getType() {
        return type;
    }

    public void setType(ProductGroupType type) {
        this.type = type;
    }
}
