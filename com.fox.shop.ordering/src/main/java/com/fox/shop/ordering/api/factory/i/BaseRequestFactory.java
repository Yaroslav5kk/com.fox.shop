package com.fox.shop.ordering.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

public interface BaseRequestFactory {
    /*----------------------------------------------users-------------------------------------------------*/
    HttpUriRequest getUserById(long id);
}
