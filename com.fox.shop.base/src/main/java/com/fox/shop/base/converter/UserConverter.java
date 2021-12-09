package com.fox.shop.base.converter;

import com.fox.shop.base.entity.UserEntity;
import com.fox.protocol.user.UserModel;

public class UserConverter {


    public static UserEntity modelToEntity(final UserModel userModel) {
        return new UserEntity().
                id(userModel.getId()).
                firstName(userModel.getFirstName()).
                username(userModel.getUsername()).
                password(userModel.getPassword()).
                role(userModel.getRole()).
                phone(userModel.getPhone());
    }

    public static UserModel entityToModel(final UserEntity userEntity) {
        return new UserModel().id(userEntity.getId()).
                firstName(userEntity.getFirstName()).
                username(userEntity.getUsername()).
                password(userEntity.getPassword()).
                role(userEntity.getRole()).
                phone(userEntity.getPhone());
    }
}
