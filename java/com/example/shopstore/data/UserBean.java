package com.example.shopstore.data;

public class UserBean extends User {
    private String password;

    public UserBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
