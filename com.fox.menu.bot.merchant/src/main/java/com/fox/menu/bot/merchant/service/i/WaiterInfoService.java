package com.fox.menu.bot.merchant.service.i;

import com.fox.menu.bot.merchant.model.WaiterInfoModel;

public interface WaiterInfoService {
    WaiterInfoModel save(WaiterInfoModel model);

    WaiterInfoModel get(long id);
}
