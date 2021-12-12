package com.fox.shop.base.entity;


import com.fox.protocol.user.Role;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;
    private String firstName;
    @Column(unique = true)
    private String username;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity id(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserEntity username(String username) {
        this.username = username;
        return this;
    }

    public UserEntity password(String password) {
        this.password = password;
        return this;
    }

    public UserEntity role(Role role) {
        this.role = role;
        return this;
    }

    public UserEntity phone(String phone) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, username, password);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
