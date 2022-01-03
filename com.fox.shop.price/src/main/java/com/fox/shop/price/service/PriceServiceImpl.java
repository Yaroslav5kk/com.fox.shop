package com.fox.shop.price.service;

import com.fox.shop.price.converter.PriceConverter;
import com.fox.shop.price.entity.PriceEntity;
import com.fox.shop.price.protocol.model.full.PriceModel;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import com.fox.shop.price.protocol.model.request.PriceOnCreateRequest;
import com.fox.shop.price.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceConverter priceConverter;
    private final PriceRepository priceRepository;

    public PriceServiceImpl(
            final PriceConverter priceConverter,
            final PriceRepository priceRepository
    ) {
        this.priceConverter = priceConverter;
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceModel save(final PriceOnCreateRequest request) {
        return priceConverter.entityToModel(priceRepository
                .save(priceConverter.requestToEntity(request)));
    }

    @Override
    public ProductPriceModel getByProductId(
            final long productId,
            final int quantity
    ) {
        return build(priceRepository.getByProductId(productId), quantity);
    }

    @Override
    public List<ProductPriceModel> getByProductIds(
            final List<Long> productIds
    ) {
        return priceRepository.getAllByProductIdIsIn(productIds)
                .stream()
                .map(priceEntity -> build(priceEntity, 1))
                .collect(Collectors.toList());
    }

    private ProductPriceModel build(
            final PriceEntity price,
            final int quantity
    ) {
        final ProductPriceModel result = new ProductPriceModel(price.getProductId(), price.getPrice());
        result.setCurrency(price.getCurrency());
        result.setPrice(price.getPrice() * quantity);
        result.setPriceToView(String.valueOf(result.getPrice()));
        return result;
    }
}
