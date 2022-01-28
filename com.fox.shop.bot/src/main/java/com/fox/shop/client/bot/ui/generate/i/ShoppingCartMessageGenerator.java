package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.List;

public interface ShoppingCartMessageGenerator {

    SendMessage getCartSession(
            long chatId,
            FullCartSessionModel cartSession
    );

    /*List<SendPhotoFileIdRequest> viewCartItems(
            long chatId,
            FullCartSessionModel cartSession
    );*/

    List<SendPhotoFileIdRequest> editCartSessionTitle(
            long chatId,
            FullCartSessionModel cartSession
    );

    SendMessage setItemQuantityTitle(
            long chatId
    );

    SendMessage emptyCartSession(
            long chatId
    );

    SendMessage successClearCartSession(
            long chatId
    );

    SendPhoto successAddToCart(
            long chatId,
            long cartSessionId
    );
}
