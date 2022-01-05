package com.fox.shop.protocol;

import java.util.List;

public class ProductModel {
    private long id;
    private String name;
    private String description;
    private String mainImageStorageId;
    private String[] imagesStorageIds;
    private MerchantModel merchant;
    private ProductBalanceModel productBalance;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MerchantModel getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModel merchant) {
        this.merchant = merchant;
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

    public ProductBalanceModel getProductBalance() {
        return productBalance;
    }

    public void setProductBalance(ProductBalanceModel productBalance) {
        this.productBalance = productBalance;
    }
}
