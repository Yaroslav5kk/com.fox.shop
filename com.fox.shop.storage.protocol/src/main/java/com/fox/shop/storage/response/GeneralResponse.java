package com.fox.shop.storage.response;

public class GeneralResponse<T> {
  private T result;

  public GeneralResponse() {
  }

  public GeneralResponse(T result) {
    this.result = result;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
