package com.fox.shop.client.bot.service.answer;

import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.service.i.AnswerHandler;
import com.fox.shop.client.bot.ui.scenarios.i.OrderScenarios;
import com.fox.shop.client.bot.ui.scenarios.i.SearchScenarios;
import com.fox.shop.client.bot.ui.scenarios.i.ShoppingCartScenarios;
import com.fox.shop.client.bot.ui.scenarios.i.StartScenariosMenu;
import com.fox.shop.client.bot.utils.extractor.UpdateExtractor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class AnswerHandlerImpl implements AnswerHandler {

    private final UserDomainStateContext domainStateContext;
    private final OrderScenarios orderScenarios;
    private final UserHistoryContext userHistoryContext;
    private final SearchScenarios searchScenarios;
    private final ShoppingCartScenarios shoppingCartScenarios;
    private final StartScenariosMenu startScenariosMenu;

    public AnswerHandlerImpl(
            final UserDomainStateContext domainStateContext,
            final OrderScenarios orderScenarios,
            final UserHistoryContext userHistoryContext,
            final SearchScenarios searchScenarios,
            final ShoppingCartScenarios shoppingCartScenarios,
            final StartScenariosMenu startScenariosMenu
    ) {
        this.domainStateContext = domainStateContext;
        this.orderScenarios = orderScenarios;
        this.userHistoryContext = userHistoryContext;
        this.searchScenarios = searchScenarios;
        this.shoppingCartScenarios = shoppingCartScenarios;
        this.startScenariosMenu = startScenariosMenu;
    }

    @Override
    public void handle(
            final Update update
    ) {
        final long chatId = UpdateExtractor.chatId(update);
        userHistoryContext.chatIdMessage(chatId, UpdateExtractor.messageId(update));
        final User user = UpdateExtractor.user(update);
        final String enteredText = UpdateExtractor.enteredText(update);
        final Integer userId = UpdateExtractor.userId(update);
        final UserDomainState currentDomainState = domainStateContext.current(userId);
        switch (currentDomainState) {
            case SET_NAME:
                startScenariosMenu.getNameHandle(chatId, enteredText, userId);
                break;
            case SET_PHONE:
                startScenariosMenu.getPhoneHandle(chatId, user, UpdateExtractor.phone(update).orElse(enteredText));
                break;
            case SEARCH_PRODUCT:
                searchScenarios.searchHandle(chatId, userId, enteredText);
                break;
            case SET_ITEM_QUANTITY_ON_ADD_TO_CART:
                shoppingCartScenarios.setCartItemQuantityOnUpdateHandle(chatId, userId, Short.valueOf(enteredText));
                break;
            case SET_ITEM_QUANTITY_ON_UPDATE_HANDLE:
                shoppingCartScenarios.setCartItemQuantityOnUpdateHandle(chatId, userId, Short.valueOf(enteredText));
                break;
        }
    }
}














