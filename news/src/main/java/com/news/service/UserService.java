package com.news.service;

import com.news.dao.UserDao;
import com.news.dao.impl.UserDaoImpl;
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

    private UserDao userDao;

    private void init() {
        userDao = new UserDaoImpl();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public boolean checkUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }
}
