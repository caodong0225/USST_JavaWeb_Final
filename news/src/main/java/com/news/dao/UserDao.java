package com.news.dao;

import com.news.model.User;

public interface UserDao {
    boolean addUser(User user);

    User getUserByUsername(String username);
}
