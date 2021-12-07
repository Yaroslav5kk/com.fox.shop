package com.fox.shop.price.converter;

import com.fox.shop.price.entity.PriceEntity;
import com.fox.shop.price.protocol.model.full.PriceModel;
import com.fox.shop.price.protocol.model.request.PriceOnCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class PriceConverterImpl implements PriceConverter {

    private final DiscountConverter discountConverter;

    public PriceConverterImpl(
            final DiscountConverter discountConverter
    ) {
        this.discountConverter = discountConverter;
    }

    @Override
    public PriceEntity requestToEntity(final PriceOnCreateRequest request) {
        final PriceEntity result = new PriceEntity();
        result.setProductId(request.getProductId());
        result.setPrice(request.getPrice());
        result.setCurrency(request.getCurrency());
        if (!CollectionUtils.isEmpty(request.getDiscounts()))
            result.setDiscounts(request.getDiscounts().stream().map(discountConverter::requestToEntity)
                    .collect(Collectors.toList())
            );
        return result;
    }

    @Override
    public PriceModel entityToModel(final PriceEntity entity) {
        final PriceModel result = new PriceModel();
        result.setProductId(entity.getProductId());
        result.setPrice(entity.getPrice());
        result.setCurrency(entity.getCurrency());
        result.setCreatedAt(entity.getCreatedAt());
        result.setModifiedAt(entity.getModifiedAt());
        result.setDiscounts(entity.getDiscounts().stream().map(discountConverter::entityToModel)
                .collect(Collectors.toList())
        );
        return result;
    }
}
