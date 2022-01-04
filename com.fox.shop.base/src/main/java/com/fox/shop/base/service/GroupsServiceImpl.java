package com.fox.shop.base.service;

import com.fox.shop.base.converter.ProductGroupConverter;
import com.fox.shop.base.entity.ProductGroupEntity;
import com.fox.shop.base.repository.ProductGroupRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.base.service.i.GroupsService;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.request.GroupOnCreateRequest;
import com.fox.shop.protocol.type.ProductGroupType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupsServiceImpl implements GroupsService {

    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;

    public GroupsServiceImpl(
            final ProductGroupRepository productGroupRepository,
            final ProductRepository productRepository
    ) {
        this.productGroupRepository = productGroupRepository;
        this.productRepository = productRepository;
    }

    /*------------------------------------------product----------------------------------------------------------*/
    @Override
    public ProductGroupModel addProductToGroup(
            final long productId,
            final long groupId
    ) {
        final ProductGroupEntity groupEntity = productGroupRepository.getOne(groupId);
        groupEntity.getProducts().add(productRepository.getOne(productId));
        return ProductGroupConverter.fromEntity(productGroupRepository.save(groupEntity));
    }

    @Override
    public ProductGroupModel saveProductGroup(final GroupOnCreateRequest request) {
        final ProductGroupEntity toSave = ProductGroupConverter.fromRequestToEntity(request);
        return ProductGroupConverter.fromEntity(productGroupRepository.save(toSave));
    }

    @Override
    public List<ProductGroupModel> findAllProductGroup() {
        return productGroupRepository.findAll().stream().
                map(ProductGroupConverter::fromEntity).
                collect(Collectors.toList());
    }

    @Override
    public List<ProductGroupModel> findAllProductGroupByType(final ProductGroupType type) {
        return productGroupRepository.findAllByType(type).stream().
                map(ProductGroupConverter::fromEntity).
                collect(Collectors.toList());
    }
}
