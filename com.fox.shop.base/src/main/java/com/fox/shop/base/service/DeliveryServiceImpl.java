package com.fox.shop.base.service;

import com.fox.shop.base.converter.DeliveryConverter;
import com.fox.shop.base.repository.DeliveryRepository;
import com.fox.shop.base.service.i.DeliveryService;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.request.DeliveryOnCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(
            final DeliveryRepository deliveryRepository
    ) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public DeliveryModel save(final DeliveryOnCreateRequest request) {
        return DeliveryConverter.fromEntityToModel(deliveryRepository.save(DeliveryConverter.fromRequestOnCreateToEntity(request)));
    }


    @Override
    public List<DeliveryModel> getAll() {
        return deliveryRepository.findAll()
                .stream()
                .map(DeliveryConverter::fromEntityToModel)
                .collect(Collectors.toList());
    }

}



















