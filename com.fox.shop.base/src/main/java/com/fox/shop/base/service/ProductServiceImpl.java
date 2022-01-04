package com.fox.shop.base.service;

import com.fox.shop.base.converter.ProductConverter;
import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.base.repository.ProductGroupRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.base.service.i.ProductService;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

    public ProductServiceImpl(
            final ProductRepository productRepository,
            final ProductGroupRepository productGroupRepository
    ) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
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
    public List<ProductModel> allByGroup(final long groupId) {
        return productGroupRepository.getOne(groupId).getProducts().
                stream().
                filter(product -> product.getProductBalance().getBalance() > 0).
                map(ProductConverter::fromEntity).
                collect(Collectors.toList());
    }

    @Override
    public List<ProductModel> byIds(final List<Long> ids) {
        return productRepository.findAllById(ids).stream().
                map(ProductConverter::fromEntity).
                collect(Collectors.toList());
    }

    @Override
    public List<ProductModel> searchByNameMatch(final String toSearch) {
        return productRepository.findByNameContaining(toSearch).stream().
                filter(product -> product.getProductBalance().getBalance() > 0).
                map(ProductConverter::fromEntity).
                collect(Collectors.toList());
    }

}
