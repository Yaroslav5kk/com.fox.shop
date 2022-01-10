package com.fox.shop.base.service;

import com.fox.shop.base.converter.ProductConverter;
import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.base.entity.specefication.ProductSpecificationFactory;
import com.fox.shop.base.repository.ProductGroupRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.base.service.i.ProductService;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductGroupRepository productGroupRepository;
  private final ProductSpecificationFactory productSpecificationFactory;

  public ProductServiceImpl(
      final ProductRepository productRepository,
      final ProductGroupRepository productGroupRepository,
      final ProductSpecificationFactory productSpecificationFactory
  ) {
    this.productRepository = productRepository;
    this.productGroupRepository = productGroupRepository;
    this.productSpecificationFactory = productSpecificationFactory;
  }

  @Override
  public ProductModel save(final ProductOnCreateRequest request) {
    final ProductEntity toDb = ProductConverter.fromRequestOnCreateToEntity(request);
    return ProductConverter.fromEntity(productRepository.save(toDb));
  }

  @Override
  public ProductModel get(final long id) {
    return ProductConverter.fromEntity(productRepository.getOne(id));
  }


  @Override
  public Page<ProductModel> allByGroup(
      final long groupId,
      final Pageable pageable
  ) {
    final Specification<ProductEntity> specification = productSpecificationFactory.byGroupId(groupId)
        .and(productSpecificationFactory.productBalanceGreaterThan((short) 0));
    return productRepository.findAll(specification, pageable).
        map(ProductConverter::fromEntity);
  }

  @Override
  public Page<ProductModel> byIds(
      final List<Long> ids,
      final Pageable pageable
  ) {
    final Specification<ProductEntity> specification = productSpecificationFactory.productBalanceGreaterThan((short) 0)
        .and(productSpecificationFactory.byIds(ids));
    return productRepository.findAll(specification, pageable).
        map(ProductConverter::fromEntity);
  }

  @Override
  public Page<ProductModel> searchByNameMatch(
      final String toSearch,
      final Pageable pageable
  ) {
    final Specification<ProductEntity> specification = productSpecificationFactory.productBalanceGreaterThan((short) 0)
        .and(productSpecificationFactory.productNameILike(toSearch).or(productSpecificationFactory.productDescriptionILike(toSearch)));
    return productRepository.findAll(specification, pageable).
        map(ProductConverter::fromEntity);
  }

}
