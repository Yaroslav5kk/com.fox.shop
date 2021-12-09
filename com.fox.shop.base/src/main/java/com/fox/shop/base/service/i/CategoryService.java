package com.fox.shop.base.service.i;


import com.fox.shop.protocol.CategoryModel;
import com.fox.shop.protocol.request.CategoryOnCreateRequest;

public interface CategoryService {

    CategoryModel save(CategoryOnCreateRequest categoryRequest);

}
