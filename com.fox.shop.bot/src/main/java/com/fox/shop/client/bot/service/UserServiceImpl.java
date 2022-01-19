package com.fox.shop.client.bot.service;

import com.fox.protocol.user.Role;
import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.entity.UserInfoEntity;
import com.fox.shop.client.bot.model.wrapper.UserWrapper;
import com.fox.shop.client.bot.repository.UserInfoRepository;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.utils.UserValueGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final static Role ROLE_CUSTOMER = Role.USER;

  private final UserValueGenerator valueGenerator;
  private final UserInfoRepository userInfoRepository;
  private final BaseApiClient baseApiClient;
  private final UserModelDataContext userModelDataContext;

  public UserServiceImpl(
      final UserValueGenerator valueGenerator,
      final UserInfoRepository userInfoRepository,
      final BaseApiClient baseApiClient,
      final UserModelDataContext userModelDataContext
  ) {
    this.valueGenerator = valueGenerator;
    this.userInfoRepository = userInfoRepository;
    this.baseApiClient = baseApiClient;
    this.userModelDataContext = userModelDataContext;
  }

  @Override
  public void createCustomer(
      final int userId,
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
      final int userId,
      final String userName
  ) {
    final UserModel result = userModelDataContext.getRegisterUserModel(userId);
    result.setPassword("to remove. test password");
    result.setRole(ROLE_CUSTOMER);
    result.setUsername(userName);
    return result;
  }

}
