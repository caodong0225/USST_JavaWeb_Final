package com.news.dao.impl;

import com.news.dao.UserDao;
import com.news.model.User;
import com.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Connection connection;
    public UserDaoImpl() {
        init();
    }
    private void init() {
        try {
            connection = JdbcUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (username, password) VALUES (?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
