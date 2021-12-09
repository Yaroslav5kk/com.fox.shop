package com.fox.shop.shoppingcart.protocol.model.response;

public class GeneralResponse<T extends FatherResponse> {
    private T body;

    public GeneralResponse() {
    }

    public GeneralResponse(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}