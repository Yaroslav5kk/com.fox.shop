package com.fox.shop.base.service.i;

import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.request.DeliveryOnCreateRequest;

import java.util.List;

public interface DeliveryService {
    DeliveryModel save(DeliveryOnCreateRequest request);

    List<DeliveryModel> getAll();
}
