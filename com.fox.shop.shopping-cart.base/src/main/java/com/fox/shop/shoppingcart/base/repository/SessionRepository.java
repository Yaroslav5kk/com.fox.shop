package com.fox.shop.shoppingcart.base.repository;

import com.fox.shop.shoppingcart.base.entity.CartItemEntity;
import com.fox.shop.shoppingcart.base.entity.SessionEntity;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    SessionEntity findByUserIdAndOriginType(long userId, SessionOriginType originType);

    boolean existsByUserIdAndOriginType(long userId, SessionOriginType originType);

    SessionEntity findByItemsContaining(CartItemEntity cartItemEntity);
}
