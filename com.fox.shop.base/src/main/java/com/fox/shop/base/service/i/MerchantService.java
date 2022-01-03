package com.fox.shop.base.service.i;

import com.fox.shop.protocol.MerchantModel;

public interface MerchantService {
    MerchantModel save(
            MerchantModel merchant
    );

    MerchantModel getMerchantByProductId(long productId);
}
