package com.fox.shop.price.service;

import com.fox.shop.price.converter.DiscountConverter;
import com.fox.shop.price.protocol.model.full.DiscountModel;
import com.fox.shop.price.protocol.model.request.DiscountOnCreateRequest;
import com.fox.shop.price.repository.DiscountRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountConverter discountConverter;

    public DiscountServiceImpl(
            final DiscountRepository discountRepository,
            final DiscountConverter discountConverter
    ) {
        this.discountRepository = discountRepository;
        this.discountConverter = discountConverter;
    }

    @Override
    public DiscountModel save(final DiscountOnCreateRequest request) {
        return discountConverter.entityToModel(discountRepository
                .save(discountConverter.requestToEntity(request)));
    }
}
