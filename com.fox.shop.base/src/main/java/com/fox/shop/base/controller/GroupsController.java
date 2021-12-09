package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.GroupsService;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.request.GroupOnCreateRequest;
import com.fox.shop.protocol.type.ProductGroupType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/groups")
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(
            final GroupsService groupsService
    ) {
        this.groupsService = groupsService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProductGroupModel> addProduct(
            @RequestParam final long productId,
            @RequestParam final long groupId
    ) {
        return ResponseEntity.ok(groupsService.addProductToGroup(productId, groupId));
    }

    @PostMapping("/save/product-group")
    public ResponseEntity<ProductGroupModel> saveProductGroup(
            @RequestBody final GroupOnCreateRequest request
    ) {
        return ResponseEntity.ok(groupsService.saveProductGroup(request));
    }

    @GetMapping("/product-group")
    public ResponseEntity<List<ProductGroupModel>> getAllProduct(
            @RequestParam final ProductGroupType type
    ) {
        return ResponseEntity.ok(groupsService.findAllProductGroupByType(type));
    }
}
