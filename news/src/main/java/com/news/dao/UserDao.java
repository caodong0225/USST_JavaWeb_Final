package com.news.dao;

import com.news.model.User;

public interface UserDao {
    public boolean addUser(User user);
    public User getUserByUsername(String username);
}
