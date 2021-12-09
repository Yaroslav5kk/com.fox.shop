package com.fox.shop.client.bot.model.response;

public class DeleteMessageResponse {
  private boolean ok;
  private boolean result;

  public DeleteMessageResponse() {
  }

  public DeleteMessageResponse(boolean ok, boolean result) {
    this.ok = ok;
    this.result = result;
  }

  public boolean isOk() {
    return ok;
  }

  public void setOk(boolean ok) {
    this.ok = ok;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public static DeleteMessageResponse failed() {
    return new DeleteMessageResponse(false, false);
  }
}
