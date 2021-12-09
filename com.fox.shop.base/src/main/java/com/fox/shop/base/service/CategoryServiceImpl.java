package com.fox.shop.base.service;

import com.fox.shop.base.converter.CategoryConverter;
import com.fox.shop.base.entity.CategoryEntity;
import com.fox.shop.base.repository.CategoryRepository;
import com.fox.shop.base.service.i.CategoryService;
import com.fox.shop.protocol.CategoryModel;
import com.fox.shop.protocol.request.CategoryOnCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(
            final CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryModel save(final CategoryOnCreateRequest kitchenRequest) {
        final CategoryEntity toDb = CategoryConverter.fromRequestOnCreateToEntity(kitchenRequest);
        return CategoryConverter.fromEntity(categoryRepository.save(toDb));
    }

}
