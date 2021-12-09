package com.fox.menu.bot.merchant.context.i;

import com.fox.menu.bot.merchant.model.type.UserProcessState;

public interface UserProcessStateContext {
    void free(Integer userId);

    void finish(Integer userId);

    void setId(Integer userId);

    void setValue(Integer userId);

    void setUrl(Integer userId);

    void setPriority(Integer userId);

    void setDescription(Integer userId);

    void setImage(Integer userId);

    UserProcessState current(Integer userId);

    boolean isFree(Integer userId);

    void put(Integer userId, UserProcessState userProcessState);
}
