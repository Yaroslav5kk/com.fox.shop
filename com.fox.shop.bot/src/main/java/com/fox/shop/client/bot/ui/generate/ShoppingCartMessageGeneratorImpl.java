package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.service.i.UploadImageToTelegramService;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.i.ShoppingCartMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.StartIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMessageGeneratorImpl implements ShoppingCartMessageGenerator {

    private final BaseApiClient baseApiClient;
    private final InlineKeyboardGenerator inlineKeyboardGenerator;
    private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
    private final StartIKeyboardGenerator startIKeyboardGenerator;
    private final UploadImageToTelegramService uploadImageToTelegramService;
    private final PriceApiClient priceApiClient;

    public ShoppingCartMessageGeneratorImpl(
            final BaseApiClient baseApiClient,
            final InlineKeyboardGenerator inlineKeyboardGenerator,
            final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
            final StartIKeyboardGenerator startIKeyboardGenerator,
            final UploadImageToTelegramService uploadImageToTelegramService,
            final PriceApiClient priceApiClient
    ) {
        this.baseApiClient = baseApiClient;
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
        this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
        this.startIKeyboardGenerator = startIKeyboardGenerator;
        this.uploadImageToTelegramService = uploadImageToTelegramService;
        this.priceApiClient = priceApiClient;
    }

    @Override
    public SendMessage getCartSession(
            final long chatId,
            final FullCartSessionModel cartSession
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setReplyMarkup(shoppingSessionIKeyboardGenerator.getCartSession(cartSession.getId()));
        result.setParseMode("HTML");
        result.setText(ShoppingSessionViewer.view(cartSession, getSessionTotalPrice(cartSession)));
        return result;
    }

    /*@Override
    public List<SendPhotoFileIdRequest> viewCartItems(
            final long chatId,
            final FullCartSessionModel cartSession
    ) {
        final Map<Long, Integer> productIdQuantity = cartSession.getItems()
                .stream()
                .collect(Collectors.toMap(FullCartItemModel::getProductId, FullCartItemModel::getQuantity));

        final List<ProductModel> products = baseApiClient.productByIds(new ArrayList<>(productIdQuantity.keySet()));
        final List<SendPhotoFileIdRequest> result = new LinkedList<>();
        products.forEach(itProduct -> {
            final SendPhotoFileIdRequest sendPhoto = new SendPhotoFileIdRequest();
            sendPhoto.setPhoto(uploadImageToTelegramService.getTelegramIdByMainId(itProduct.getMainImage().getId()));
            sendPhoto.setChatId(chatId);
            sendPhoto.setParseMode("HTML");
            sendPhoto.setCaption(ProductViewer.viewWithQuantity(itProduct, productIdQuantity.get(itProduct.getId())));
            result.add(sendPhoto);
        });
        return result;
    }*/

    @Override
    public List<SendPhotoFileIdRequest> editCartSessionTitle(
            final long chatId,
            final FullCartSessionModel cartSession
    ) {
        final List<SendPhotoFileIdRequest> result = new LinkedList<>();
        final List<FullCartItemModel> items = cartSession.getItems();
        final Map<Long, Long> productIdMainImageId = baseApiClient.productByIds(items
                        .stream()
                        .map(FullCartItemModel::getProductId)
                        .collect(Collectors.toList())
                )
                .stream()
                .collect(Collectors.toMap(ProductModel::getId, productModel -> productModel.getMainImage().getId()));
        for (var itItem : items) {
            final SendPhotoFileIdRequest photo = new SendPhotoFileIdRequest();
            photo.setPhoto(baseApiClient.downloadImageByteById(itItem.getProductMainImageId()));
            photo.setChatId(chatId);
            photo.setReplyMarkup(shoppingSessionIKeyboardGenerator.editSessionItem(itItem.getId()));
            photo.setParseMode("HTML");
            photo.setCaption(ShoppingSessionViewer.viewSessionItem(itItem, priceApiClient.getByProductId(itItem.getProductId(), itItem.getQuantity()).getPriceToView()));
            result.add(photo);
        }
        return result;
    }

    @Override
    public SendMessage setItemQuantityTitle(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setText(ShoppingSessionViewer.setNewCartItemQuantityTitle());
        result.setReplyMarkup(shoppingSessionIKeyboardGenerator.setQuantity());
        return result;
    }

    @Override
    public SendMessage beginBack(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setReplyMarkup(shoppingSessionIKeyboardGenerator.startBack());
        result.setParseMode("HTML");
        result.setText(ShoppingSessionViewer.beginBack());
        return result;
    }

    @Override
    public SendMessage emptyCartSession(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setReplyMarkup(shoppingSessionIKeyboardGenerator.startBack());
        result.setParseMode("HTML");
        result.setText(ShoppingSessionViewer.emptySession());
        return result;
    }

    @Override
    public SendMessage successClearCartSession(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setReplyMarkup(shoppingSessionIKeyboardGenerator.startBack());
        result.setParseMode("HTML");
        result.setText(ShoppingSessionViewer.successClearSession());
        return result;
    }

    @Override
    public SendPhoto successAddToCart(
            final long chatId,
            final long cartSessionId
    ) {
        final SendPhoto result = new SendPhoto();
        result.setChatId(chatId);
        result.setReplyMarkup(shoppingSessionIKeyboardGenerator.successAddToCart(cartSessionId));
        result.setParseMode("HTML");
        result.setCaption(ShoppingSessionViewer.successAddToCart());
        return result;
    }

    private String getSessionTotalPrice(final FullCartSessionModel cartSession) {
        return cartSession.getItems()
                .stream()
                .map(fullCartItemModel -> priceApiClient.getByProductId(
                        fullCartItemModel.getProductId(),
                        fullCartItemModel.getQuantity()).getPrice())
                .reduce(Integer::sum).get().toString();
    }

}
