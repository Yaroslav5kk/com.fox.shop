package com.fox.shop.ordering.service;

import com.fox.protocol.user.UserModel;
import com.fox.shop.ordering.api.client.i.BaseApiClient;
import com.fox.shop.ordering.api.client.i.NotifyApiClient;
import com.fox.shop.ordering.api.client.i.PriceApiClient;
import com.fox.shop.ordering.api.client.i.ShoppingCartApiClient;
import com.fox.shop.ordering.converter.i.OrderConverter;
import com.fox.shop.ordering.converter.i.OrderItemConverter;
import com.fox.shop.ordering.entity.OrderEntity;
import com.fox.shop.ordering.entity.OrderItemEntity;
import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.ordering.protocol.types.OrderStatus;
import com.fox.shop.ordering.repository.OrderRepository;
import com.fox.shop.ordering.service.i.CalculateService;
import com.fox.shop.ordering.service.i.OrderService;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final CalculateService calculateService;
    private final ShoppingCartApiClient shoppingCartApiClient;
    private final OrderItemConverter orderItemConverter;
    private final BaseApiClient baseApiClient;
    private final PriceApiClient priceApiClient;
    private final NotifyApiClient notifyApiClient;

    public OrderServiceImpl(
            final OrderRepository orderRepository,
            final OrderConverter orderConverter,
            final CalculateService calculateService,
            final ShoppingCartApiClient shoppingCartApiClient,
            final OrderItemConverter orderItemConverter,
            final BaseApiClient baseApiClient,
            final PriceApiClient priceApiClient,
            final NotifyApiClient notifyApiClient
    ) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.calculateService = calculateService;
        this.shoppingCartApiClient = shoppingCartApiClient;
        this.orderItemConverter = orderItemConverter;
        this.baseApiClient = baseApiClient;
        this.priceApiClient = priceApiClient;
        this.notifyApiClient = notifyApiClient;
    }

    @Override
    public OrderModel initOrder(final OrderOnCreateRequest request) {
        final OrderEntity toSave = orderConverter.fromOnCreateRequestToEntity(request);
        final FullCartSessionModel cartSession = shoppingCartApiClient.getCartSessionById(request.getShoppingCartSessionId());
        final UserModel user = baseApiClient.getUserById(cartSession.getUserId());
        final Map<Long, FullCartItemModel> productIdCartItem = cartSession.getItems()
                .stream()
                .collect(Collectors.toMap(FullCartItemModel::getProductId, Function.identity()));
        final List<OrderItemEntity> items = priceApiClient.allProductPriceByIds(new ArrayList<>(productIdCartItem.keySet()))
                .stream()
                .map(productPriceModel -> orderItemConverter.
                        fromFullCartItemToEntity(productIdCartItem.get(productPriceModel.getProductId()), productPriceModel.getPrice()))
                .collect(Collectors.toList());
        toSave.setPhone(user.getPhone());
        toSave.setFirstname(user.getFirstName());
        toSave.setTotalPrice(calculateService.totalPriceByOrderItems(items));
        toSave.setStatus(OrderStatus.INIT);
        final OrderEntity savedOrder = orderRepository.save(toSave);
        notifyApiClient.notifyOrder(orderConverter.fromEntityToOrderNotifyRequest(savedOrder, productIdCartItem));
        return orderConverter.fromEntityToModel(savedOrder);
    }

    @Override
    public OrderModel save(final OrderOnCreateRequest request) {
        final OrderEntity toSave = orderConverter.fromOnCreateRequestToEntity(request);
        toSave.setTotalPrice(calculateService.totalPriceByOrderItems(toSave.getItems()));
        toSave.setStatus(OrderStatus.INIT);
        return orderConverter.fromEntityToModel(orderRepository.save(toSave));
    }

    @Override
    public OrderModel get(final String id) {
        final Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        return orderEntity.isPresent()
                ? orderConverter.fromEntityToModel(orderEntity.get())
                : new OrderModel();
    }

}













