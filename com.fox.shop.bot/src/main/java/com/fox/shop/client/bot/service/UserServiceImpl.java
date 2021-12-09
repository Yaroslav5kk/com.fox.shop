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
import org.telegram.telegrambots.meta.api.objects.User;

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
    public void createCustomer(final User user) {
        final int userId = user.getId();
        if (userInfoRepository.existsByTelegramUserId(userId) && userInfoRepository.findByTelegramUserId(userId).isActivated())
            return;
        final UserModel baseUser = baseApiClient.saveUser(buildUserModel(user));
        final UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setBaseUserId(baseUser.getId());
        userInfo.setTelegramUserId(user.getId());
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

    private UserModel buildUserModel(final User user) {
        final UserModel result = userModelDataContext.getRegisterUserModel(user.getId());
        result.setPassword(valueGenerator.password(user));
        result.setRole(ROLE_CUSTOMER);
        result.setUsername(valueGenerator.username(user.getId(), user.getUserName()));
        return result;
    }

}
