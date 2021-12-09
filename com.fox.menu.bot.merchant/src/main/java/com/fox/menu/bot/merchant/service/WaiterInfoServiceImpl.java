package com.fox.menu.bot.merchant.service;

import com.fox.menu.bot.merchant.model.WaiterInfoModel;
import com.fox.menu.bot.merchant.repository.WaiterInfoRepository;
import com.fox.menu.bot.merchant.service.i.WaiterInfoService;
import org.springframework.stereotype.Service;

@Service
public class WaiterInfoServiceImpl implements WaiterInfoService {

    private final WaiterInfoRepository waiterInfoRepository;

    public WaiterInfoServiceImpl(
            final WaiterInfoRepository waiterInfoRepository
    ) {
        this.waiterInfoRepository = waiterInfoRepository;
    }

    @Override
    public WaiterInfoModel save(final WaiterInfoModel model) {
        return WaiterInfoModel.fromEntity(waiterInfoRepository.save(WaiterInfoModel.toEntity(model)));
    }

    @Override
    public WaiterInfoModel get(final long id) {
        return WaiterInfoModel.fromEntity(waiterInfoRepository.findById(id).get());
    }
}
























