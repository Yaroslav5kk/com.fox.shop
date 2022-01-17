package com.fox.shop.base.service;

import com.fox.shop.base.converter.ProductConverter;
import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.base.entity.specefication.ProductSpecificationFactory;
import com.fox.shop.base.repository.ProductGroupRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.base.service.i.ProductService;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
import com.fox.shop.protocol.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
  public PageResponse<ProductModel> allByGroup(
          final long groupId,
          final Pageable pageable
  ) {
    final Specification<ProductEntity> specification = productSpecificationFactory.byGroupId(groupId)
            .and(productSpecificationFactory.productBalanceGreaterThan((short) 0));
    final Page<ProductModel> page = productRepository.findAll(specification, pageable).
            map(ProductConverter::fromEntity);
    return new PageResponse<>()
            .currentPage(page.getNumber())
            .content(page.getContent())
            .total(page.getTotalElements())
            .isLast(page.isLast());
  }

  @Override
  public List<ProductModel> byIds(
          final List<Long> ids
  ) {
    return productRepository.findAll(productSpecificationFactory.byIds(ids))
            .stream()
            .map(ProductConverter::fromEntity)
            .collect(Collectors.toList());
  }

  @Override
  public PageResponse<ProductModel> searchByNameMatch(
          final String toSearch,
          final Pageable pageable
  ) {
    final Specification<ProductEntity> specification = productSpecificationFactory.productBalanceGreaterThan((short) 0)
            .and(productSpecificationFactory.productNameILike(toSearch).or(productSpecificationFactory.productDescriptionILike(toSearch)));
    final Page<ProductModel> page = productRepository.findAll(specification, pageable).
            map(ProductConverter::fromEntity);
    return new PageResponse<>()
            .currentPage(page.getNumber())
            .content(page.getContent())
            .total(page.getTotalElements())
            .isLast(page.isLast());
  }

}
