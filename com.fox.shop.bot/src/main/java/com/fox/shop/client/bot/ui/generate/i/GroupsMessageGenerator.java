package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;

import java.util.List;

public interface GroupsMessageGenerator {

    List<SendPhotoFileIdRequest> allSearchProductGroups(
            long chatId,
            int userId
    );

    List<SendPhotoFileIdRequest> allMainProductGroups(
            long chatId,
            int userId
    );
}
