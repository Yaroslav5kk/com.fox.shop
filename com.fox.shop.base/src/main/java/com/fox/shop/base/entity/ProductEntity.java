package com.fox.shop.base.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "product_balance_id")
    private ProductBalanceEntity productBalance;
    @JoinColumn(name = "main_image_id")
    @OneToOne
    private ImageEntity mainImage;
    @Column(name = "main_image_storage_id")
    private String mainImageStorageId;
    @ManyToOne
    private CategoryEntity category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public ImageEntity getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageEntity mainImage) {
        this.mainImage = mainImage;
    }

    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
    }

    public ProductBalanceEntity getProductBalance() {
        return productBalance;
    }

    public void setProductBalance(ProductBalanceEntity productBalance) {
        this.productBalance = productBalance;
    }

    public String getMainImageStorageId() {
        return mainImageStorageId;
    }

    public void setMainImageStorageId(String mainImageStorageId) {
        this.mainImageStorageId = mainImageStorageId;
    }
}
