package com.fox.menu.bot.merchant.context;

import com.fox.menu.bot.merchant.context.i.UserProcessStateContext;
import com.fox.menu.bot.merchant.model.type.UserProcessState;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserProcessStateContextImpl implements UserProcessStateContext {
  private final Map<Integer, UserProcessState> stateMap;

  public UserProcessStateContextImpl() {
    stateMap = new HashMap<>();
  }

  @Override
  public void free(final Integer userId) {
    stateMap.put(userId, UserProcessState.FREE);
  }

  @Override
  public void finish(final Integer userId) {
    stateMap.put(userId, UserProcessState.FINISH);
  }

  @Override
  public void setId(final Integer userId) {
    stateMap.put(userId, UserProcessState.SET_ID);
  }

  @Override
  public void setValue(final Integer userId) {
    stateMap.put(userId, UserProcessState.SET_VALUE);
  }

  @Override
  public void setUrl(final Integer userId) {
    stateMap.put(userId, UserProcessState.SET_URL);
  }

  @Override
  public void setPriority(final Integer userId) {
    stateMap.put(userId, UserProcessState.SET_PRIORITY);
  }

  @Override
  public void setDescription(final Integer userId) {
    stateMap.put(userId, UserProcessState.SET_DESCRIPTION);
  }

  @Override
  public void setImage(final Integer userId) {
    stateMap.put(userId, UserProcessState.SET_IMAGE);
  }

  @Override
  public UserProcessState current(final Integer userId) {
    if (stateMap.containsKey(userId))
      return stateMap.get(userId);
    stateMap.put(userId, UserProcessState.FREE);
    return UserProcessState.FREE;
  }

  @Override
  public boolean isFree(final Integer userId) {
    if (stateMap.containsKey(userId) && UserProcessState.FREE.equals(stateMap.get(userId)))
      return true;
    return false;
  }

  @Override
  public void put(final Integer userId, final UserProcessState userProcessState) {
    stateMap.put(userId, userProcessState);
  }
}
