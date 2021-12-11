package com.fox.shop.base.service;

import com.fox.shop.base.repository.MerchantRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.protocol.response.GeneralResponse;
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
  public GeneralResponse getMerchantIdByProductId(final long productId) {
    return new GeneralResponse(productRepository.getOne(productId).getMerchant().getId());
  }
}
