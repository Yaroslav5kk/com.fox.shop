package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.CategoryIKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.CategoryMessageGenerator;
import com.fox.shop.client.bot.ui.view.CategoryViewer;
import com.fox.shop.protocol.CategoryModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class CategoryMessageGeneratorImpl implements CategoryMessageGenerator {
    private final BaseApiClient baseApiClient;
    private final InlineKeyboardGenerator inlineKeyboardGenerator;
    private final CategoryIKeyboardGenerator categoryIKeyboardGenerator;

    public CategoryMessageGeneratorImpl(
            final BaseApiClient baseApiClient,
            final InlineKeyboardGenerator inlineKeyboardGenerator,
            final CategoryIKeyboardGenerator categoryIKeyboardGenerator
    ) {
        this.baseApiClient = baseApiClient;
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
        this.categoryIKeyboardGenerator = categoryIKeyboardGenerator;
    }

    @Override
    public SendMessage afterAllCategory(
            final long chatId,
            final int userId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(CategoryViewer.afterAllCategory());
        result.setReplyMarkup(categoryIKeyboardGenerator.afterAllCategory(userId));
        result.setParseMode("HTML");
        return result;
    }

    private SendPhotoFileIdRequest category(
            final long chatId,
            final CategoryModel category,
            final String fileId
    ) {
        final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
        result.setChatId(chatId);
        result.setCaption(viewCategory(category));
        result.setParseMode("HTML");
        result.setReplyMarkup(categoryIKeyboardGenerator.category(category.getId()));
        result.setPhoto(fileId);
        return result;
    }


    private String viewCategory(final CategoryModel wrapper) {
        return CategoryViewer.view(wrapper);
    }

}
















