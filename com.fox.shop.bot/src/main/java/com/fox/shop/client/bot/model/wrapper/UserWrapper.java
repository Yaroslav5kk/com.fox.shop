package com.fox.shop.client.bot.model.wrapper;


import com.fox.protocol.user.Role;
import com.fox.protocol.user.UserModel;

public class UserWrapper {
    private final UserModel userModel;

    public UserWrapper() {
        userModel = new UserModel();
    }

    public UserWrapper(final UserModel userModel) {
        this.userModel = userModel;
    }

    public Long getId() {
        return userModel.getId();
    }

    public String getFirstName() {
        return userModel.getFirstName();
    }

    public void setFirstName(String firstName) {
        userModel.setFirstName(firstName);
    }

    public String getLastName() {
        return userModel.getLastName();
    }

    public void setLastName(String lastName) {
        userModel.setLastName(lastName);
    }

    public String getUsername() {
        return userModel.getUsername();
    }

    public void setUsername(String username) {
        userModel.setUsername(username);
    }

    public String getPassword() {
        return userModel.getPassword();
    }

    public void setPassword(String password) {
        userModel.setPassword(password);
    }

    public Role getRole() {
        return userModel.getRole();
    }

    public void setRole(Role role) {
        userModel.setRole(role);
    }

    public UserModel getUserModel() {
        return userModel;
    }

}
