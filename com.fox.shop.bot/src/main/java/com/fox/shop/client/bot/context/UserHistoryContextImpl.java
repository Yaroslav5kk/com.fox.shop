package com.fox.shop.client.bot.context;

import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.context.model.UserHistoryStateModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

@Component
public class UserHistoryContextImpl implements UserHistoryContext {
  private final Map<Integer, Stack<UserHistoryStateModel>> userIdHistory;
  private final Map<Long, LinkedList<Long>> chatIdMessages;
  private final UserProcessStateContext userProcessStateContext;
  private final UserDomainStateContext userDomainStateContext;
  private final UserModelDataContext userModelDataContext;
  private final Map<Long, Long> productIdMessageId;

  public UserHistoryContextImpl(
      final UserProcessStateContext userProcessStateContext,
      final UserDomainStateContext userDomainStateContext,
      final UserModelDataContext userModelDataContext
  ) {
    this.userProcessStateContext = userProcessStateContext;
    this.userDomainStateContext = userDomainStateContext;
    this.userModelDataContext = userModelDataContext;
    userIdHistory = new HashMap<>();
    chatIdMessages = new HashMap<>();
    productIdMessageId = new HashMap<>();
  }

  @Override
  public void productIdMessageId(
      final Long productId,
      final Long messageId
  ) {
    productIdMessageId.put(productId, messageId);
  }

  @Override
  public void chatIdMessage(
      final Long chatId,
      final Long messageId
  ) {
    if (chatIdMessages.containsKey(chatId)) {
      chatIdMessages.get(chatId).push(messageId);
      return;
    }
    final LinkedList<Long> toSave = new LinkedList<>();
    toSave.push(messageId);
    chatIdMessages.put(chatId, toSave);
  }

  @Override
  public LinkedList<Long> getAndRemoveMessagesByChatId(final Long chatId) {
    final LinkedList<Long> result = chatIdMessages.get(chatId);
    chatIdMessages.remove(chatId);
    return result == null
        ? new LinkedList<>()
        : result;
  }

  @Override
  public void snapshot(final int userId, final String command) {
    final UserHistoryStateModel userHistory = new UserHistoryStateModel(userId);
    userHistory.setCommand(command);
    userHistory.setDomainState(userDomainStateContext.current(userId));
    userHistory.setProcessState(userProcessStateContext.current(userId));
    userHistory.setCartItemOnCreateRequest(userModelDataContext.getCartItem(userId));
    userHistory.setOrderOnCreateRequest(userModelDataContext.getOrderOnCreateRequest(userId));
    if (userIdHistory.containsKey(userId)) {
      userIdHistory.get(userId).push(userHistory);
      return;
    }
    final Stack stack = new Stack<>();
    stack.push(userHistory);
    userIdHistory.put(userId, stack);
  }

  @Override
  public String back(final int userId) {
    final UserHistoryStateModel userHistory = userIdHistory.get(userId).pop();
    userDomainStateContext.put(userId, userHistory.getDomainState());
    userProcessStateContext.put(userId, userHistory.getProcessState());
    userModelDataContext.cartItems(userId, userHistory.getCartItemOnCreateRequest());
    userModelDataContext.orderOnCreateRequest(userId, userHistory.getOrderOnCreateRequest());
    return userHistory.getCommand();
  }
}
