package com.fox.shop.notify.protocol.response;

public class GeneralResponse {
  private String result;

  public GeneralResponse() {
  }

  public GeneralResponse(String result) {
    this.result = result;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
