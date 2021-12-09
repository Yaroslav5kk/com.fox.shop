package com.fox.shop.shoppingcart.protocol.model.request;

public class GeneralRequest<T extends FatherRequest> {
    private T body;

    public GeneralRequest() {
    }

    public GeneralRequest(T body) {
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
