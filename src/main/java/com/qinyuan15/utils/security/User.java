package com.qinyuan15.utils.security;

import com.qinyuan15.utils.hibernate.PersistObject;

/**
 * Persist object of user
 * Created by qinyuan on 15-3-5.
 */
public class User extends PersistObject {
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
