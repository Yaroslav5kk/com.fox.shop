package com.fox.shop.client.bot.context.i;

import com.fox.shop.client.bot.model.types.UserProcessState;

public interface UserProcessStateContext {
    void free(Integer userId);

    void finish(Integer userId);

    void setId(Integer userId);

    void setValue(Integer userId);

    void setUrl(Integer userId);

    void setPriority(Integer userId);

    void setDescription(Integer userId);

    void setImage(Integer userId);

    void deleteCartItem(Integer userId);

    void plusOne(Integer userId);

    void minusOne(Integer userId);

    UserProcessState current(Integer userId);

  boolean isFree(Integer userId);

    void put(Integer userId, UserProcessState userProcessState);
}
