package com.fox.shop.protocol.request;

import java.util.ArrayList;
import java.util.List;

public class ProductOnCreateRequest {
    private String name;
    private String description;
    private String mainImageStorageId;
    private long categoryId;;
    private long merchantId;
    private String[] imagesStorageIds;

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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMainImageStorageId() {
        return mainImageStorageId;
    }

    public void setMainImageStorageId(String mainImageStorageId) {
        this.mainImageStorageId = mainImageStorageId;
    }

    public String[] getImagesStorageIds() {
        return imagesStorageIds;
    }

    public void setImagesStorageIds(String[] imagesStorageIds) {
        this.imagesStorageIds = imagesStorageIds;
    }
}
