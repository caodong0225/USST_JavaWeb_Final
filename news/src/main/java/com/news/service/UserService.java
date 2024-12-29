package com.news.service;

import com.news.model.User;

public class UserService {

    private static UserService instance;
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
            instance.init();
        }
        return instance;
    }
    private void init() {
    }

    public User getUserByUsername(String username) {
        return null;
    }
    public boolean isUserOnline(String username) {
        return false;
    }

    public boolean setUserOnline(String username, boolean online) {
        return false;
    }
    public boolean checkUser(String username, String password) {
        return false;
    }
    public boolean addUser(User user) {
        return false;
    }
}
