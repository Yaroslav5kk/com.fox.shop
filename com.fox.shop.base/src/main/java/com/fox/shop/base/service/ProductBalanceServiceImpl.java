package com.fox.shop.base.service;

import com.fox.shop.base.converter.ProductBalanceConverter;
import com.fox.shop.base.entity.ProductBalanceEntity;
import com.fox.shop.base.repository.ProductBalanceRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.base.service.i.ProductBalanceService;
import com.fox.shop.protocol.ProductBalanceModel;
import com.fox.shop.protocol.request.ProductBalanceOnCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductBalanceServiceImpl implements ProductBalanceService {


  private final ProductBalanceRepository productBalanceRepository;
  private final ProductRepository productRepository;

  public ProductBalanceServiceImpl(
          final ProductBalanceRepository productBalanceRepository,
          final ProductRepository productRepository
  ) {
    this.productBalanceRepository = productBalanceRepository;
    this.productRepository = productRepository;
  }

  @Override
  public ProductBalanceModel save(final ProductBalanceOnCreateRequest request) {
    productRepository.findAllById(request.getProductIds())
            .forEach(product -> {
              final ProductBalanceEntity toDb = ProductBalanceConverter.fromRequestOnCreateToEntity(request);
              toDb.setProduct(product);
              product.setProductBalance(productBalanceRepository.save(toDb));
              productRepository.save(product);
            });
    return new ProductBalanceModel();
  }

}
