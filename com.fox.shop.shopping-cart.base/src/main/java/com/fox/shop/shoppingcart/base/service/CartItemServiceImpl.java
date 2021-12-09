package com.fox.shop.shoppingcart.base.service;

import com.fox.shop.shoppingcart.base.mapper.i.CartItemMapper;
import com.fox.shop.shoppingcart.base.repository.CartItemRepository;
import com.fox.shop.shoppingcart.base.service.i.CartItemService;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemServiceImpl(
            final CartItemRepository cartItemRepository,
            final CartItemMapper cartItemMapper
    ) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public SimpleResponse save(final CartItemOnCreateRequest request) {
        cartItemRepository.save(cartItemMapper.fromRequestToEntity(request));
        return SimpleResponse.ok();
    }
}
