package com.fox.protocol.user;

public class UserModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;
    private String phone;

    public UserModel id(Long id) {
        this.id = id;
        return this;
    }

    public UserModel firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserModel lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserModel username(String username) {
        this.username = username;
        return this;
    }

    public UserModel password(String password) {
        this.password = password;
        return this;
    }

    public UserModel role(Role role) {
        this.role = role;
        return this;
    }

    public UserModel phone(final String phone) {
        this.phone = phone;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
