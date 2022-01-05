package com.fox.shop.base.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_balance")
public class ProductBalanceEntity {
  @Id
  @GeneratedValue
  private long id;
  @OneToOne
  private ProductEntity product;
  private short balance = 0;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  public short getBalance() {
    return balance;
  }

  public void setBalance(short balance) {
    this.balance = balance;
  }


}
