package com.fox.shop.base.converter;

import com.fox.shop.base.entity.MerchantEntity;
import com.fox.shop.protocol.MerchantModel;

public class MerchantConverter {

    public static MerchantModel fromEntity(final MerchantEntity entity) {
        final MerchantModel result = new MerchantModel();
        result.setId(entity.getId());
        result.setName(entity.getName());
        return result;
    }

    public static MerchantEntity toEntity(final MerchantModel model) {
        final MerchantEntity result = new MerchantEntity();
        result.setId(model.getId());
        result.setName(model.getName());
        return result;
    }

}
