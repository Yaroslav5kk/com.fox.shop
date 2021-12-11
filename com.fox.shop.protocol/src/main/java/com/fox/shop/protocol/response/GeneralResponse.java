package com.fox.shop.protocol.response;

public class GeneralResponse<T> {
  private T data;

  public GeneralResponse() {
  }

  public GeneralResponse(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
