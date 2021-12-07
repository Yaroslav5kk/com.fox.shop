package com.fox.shop.notify.protocol.response;

public class NotifyResponse extends GeneralResponse {

  public NotifyResponse() {
  }

  public NotifyResponse(String result) {
    super(result);
  }

  public static NotifyResponse ok() {
    return new NotifyResponse("OK");
  }

  public static NotifyResponse fail() {
    return new NotifyResponse("FAIL");
  }
}
