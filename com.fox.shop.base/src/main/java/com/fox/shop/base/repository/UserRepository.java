package com.fox.shop.base.repository;

import com.fox.shop.base.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    void deleteByUsername(String username);

    UserEntity getByUsername(String username);

    Optional<UserEntity> findFirstByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
