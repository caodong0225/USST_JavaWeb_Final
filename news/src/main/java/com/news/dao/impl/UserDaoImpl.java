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
            var sql = "INSERT INTO Users (username, password,birthday,sex,career,country,educationBackground) VALUES (?, ?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
            preparedStatement.setString(4, user.getSex());
            preparedStatement.setString(5, user.getCareer());
            preparedStatement.setString(6, user.getCountry());
            preparedStatement.setString(7, user.getEducationBackground());
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
                user.setBirthday(resultSet.getDate("birthday"));
                user.setSex(resultSet.getString("sex"));
                user.setCareer(resultSet.getString("career"));
                user.setCountry(resultSet.getString("country"));
                user.setEducationBackground(resultSet.getString("educationBackground"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
