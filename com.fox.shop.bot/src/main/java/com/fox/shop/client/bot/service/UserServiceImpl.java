package com.fox.shop.client.bot.service;

import com.fox.protocol.user.Role;
import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.entity.UserInfoEntity;
import com.fox.shop.client.bot.model.wrapper.UserWrapper;
import com.fox.shop.client.bot.repository.UserInfoRepository;
import com.fox.shop.client.bot.service.i.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final static Role ROLE_CUSTOMER = Role.USER;

  private final UserInfoRepository userInfoRepository;
  private final BaseApiClient baseApiClient;
  private final TgUserSessionContext tgUserSessionContext;

  public UserServiceImpl(
      final UserInfoRepository userInfoRepository,
      final BaseApiClient baseApiClient,
      final TgUserSessionContext tgUserSessionContext
  ) {
    this.userInfoRepository = userInfoRepository;
    this.baseApiClient = baseApiClient;
    this.tgUserSessionContext = tgUserSessionContext;
  }

  @Override
  public void createCustomer(
      final long userId,
      final String userName
  ) {
    if (userInfoRepository.existsByTelegramUserId(userId) && userInfoRepository.findByTelegramUserId(userId).isActivated())
      return;
    final UserModel baseUser = baseApiClient.saveUser(buildUserModel(userId, userName));
    final UserInfoEntity userInfo = new UserInfoEntity();
    userInfo.setBaseUserId(baseUser.getId());
    userInfo.setTelegramUserId(userId);
    userInfo.setActivated(true);
    userInfoRepository.save(userInfo);
  }

  @Override
  public long getBaseUserIdByTelegramId(final long telegramId) {
    return userInfoRepository.findByTelegramUserId(telegramId).getBaseUserId();
  }

  @Override
  public boolean isActivatedUser(final long telegramUserId) {
    if (!userInfoRepository.existsByTelegramUserId(telegramUserId))
      return false;
    return userInfoRepository.findByTelegramUserId(telegramUserId).isActivated();
  }

  @Override
  public UserWrapper getByUsername(final String username) {
    return new UserWrapper();
  }

  private UserModel buildUserModel(
      final long userId,
      final String userName
  ) {
    final UserModel result = tgUserSessionContext.getSession(userId).getUserModel();
    result.setPassword("to remove. test password");
    result.setRole(ROLE_CUSTOMER);
    result.setUsername(userName);
    return result;
  }

}
