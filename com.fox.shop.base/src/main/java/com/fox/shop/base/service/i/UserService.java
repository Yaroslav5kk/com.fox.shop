package com.fox.shop.base.service.i;


import com.fox.protocol.user.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserModel create(UserModel userModel);

    UserModel getByUsername(String username);

    Optional<UserModel> getOptionalByUsername(String username);

    boolean existByUsername(String username);

    UserModel get(Long id);

    List<UserModel> getAll();

    void deleteByUsername(String username);

    void delete(Long id);
}
