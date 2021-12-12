package com.fox.shop.base.service;

import com.fox.shop.base.converter.MerchantConverter;
import com.fox.shop.base.repository.MerchantRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.protocol.MerchantModel;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;

    public MerchantServiceImpl(
            final MerchantRepository merchantRepository,
            final ProductRepository productRepository
    ) {
        this.merchantRepository = merchantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public MerchantModel save(
            final MerchantModel merchant
    ) {
        return MerchantConverter.fromEntity(merchantRepository.save(MerchantConverter.toEntity(merchant)));
    }

    @Override
    public MerchantModel getMerchantByProductId(final long productId) {
        return MerchantConverter.fromEntity(productRepository.getOne(productId).getMerchant());
    }
}
