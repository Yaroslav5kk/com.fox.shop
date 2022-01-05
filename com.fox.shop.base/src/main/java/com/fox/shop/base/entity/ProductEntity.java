package com.fox.shop.base.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String description;
  @Column(name = "main_image_storage_id")
  private String mainImageStorageId;
  @OneToOne
  @JoinColumn(name = "product_balance_id")
  private ProductBalanceEntity productBalance;
  @ManyToOne
  private MerchantEntity merchant;
  @ManyToMany
  private List<ProductGroupEntity> groups;


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

  public List<ProductGroupEntity> getGroups() {
    return groups;
  }

  public void setGroups(List<ProductGroupEntity> groups) {
    this.groups = groups;
  }
}
