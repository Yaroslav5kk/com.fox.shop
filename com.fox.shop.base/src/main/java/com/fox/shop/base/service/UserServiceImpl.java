package com.fox.shop.base.service;


import com.fox.shop.base.converter.UserConverter;
import com.fox.shop.base.repository.UserRepository;
import com.fox.shop.base.service.i.UserService;
import com.fox.protocol.user.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(
            final UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel create(UserModel userModel) {
        if (userRepository.existsByUsername(userModel.getUsername()))
            userModel.setId(userRepository.getByUsername(userModel.getUsername()).getId());
        return UserConverter.entityToModel(
                userRepository.save(UserConverter.modelToEntity(userModel))
        );
    }

    @Override
    public UserModel getByUsername(final String username) {
        return UserConverter.entityToModel(userRepository.getByUsername(username));
    }

    @Override
    public Optional<UserModel> getOptionalByUsername(String username) {
        return userRepository.existsByUsername(username) ?
                Optional.of(UserConverter.entityToModel(userRepository.getByUsername(username))) :
                Optional.empty();
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserModel get(Long id) {
        return UserConverter.entityToModel(userRepository.getOne(id));
    }

    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll().stream().
                map(UserConverter::entityToModel).
                collect(Collectors.toList());
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
