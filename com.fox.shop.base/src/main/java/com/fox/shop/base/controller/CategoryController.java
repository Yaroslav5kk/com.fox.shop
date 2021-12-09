package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.CategoryService;
import com.fox.shop.protocol.CategoryModel;
import com.fox.shop.protocol.request.CategoryOnCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            final CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryModel> save(
            @RequestBody final CategoryOnCreateRequest categoryOnCreateRequest
    ) {
        return ResponseEntity.ok(categoryService.save(categoryOnCreateRequest));
    }
}










