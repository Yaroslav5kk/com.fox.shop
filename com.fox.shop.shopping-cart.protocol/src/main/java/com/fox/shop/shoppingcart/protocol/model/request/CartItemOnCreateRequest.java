package com.fox.shop.shoppingcart.protocol.model.request;

import java.util.Objects;

public class CartItemOnCreateRequest implements FatherRequest{
  private long productId;
  private long productMainImageId;
  private int quantity;

  public CartItemOnCreateRequest() {
  }

  public CartItemOnCreateRequest(long productId) {
    this.productId = productId;
  }

  public CartItemOnCreateRequest(long productId, int quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CartItemOnCreateRequest that = (CartItemOnCreateRequest) o;
    return productId == that.productId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId);
  }

  public long getProductMainImageId() {
    return productMainImageId;
  }

  public void setProductMainImageId(long productMainImageId) {
    this.productMainImageId = productMainImageId;
  }
}
