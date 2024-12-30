package com.news.dao.impl;

import com.news.dao.UserTagDao;
import com.news.model.UserTag;
import com.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTagDaoImpl implements UserTagDao {
    private Connection connection;
    public UserTagDaoImpl() {
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
    public boolean addUserTag(UserTag userTag) {
        try {
            var preparedStatement = connection.prepareStatement("INSERT INTO UserTags (username, tag,clickCount) VALUES (?, ?,?)");
            preparedStatement.setString(1, userTag.getUsername());
            preparedStatement.setString(2, userTag.getTag());
            preparedStatement.setInt(3, userTag.getClickCount());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getTagsByUsername(String username) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT tag FROM UserTags WHERE username = ?");
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            var tags = new ArrayList<String>();
            while (resultSet.next()) {
                tags.add(resultSet.getString("tag"));
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean updateUserTag(UserTag userTag) {
        try {
            var preparedStatement = connection.prepareStatement("UPDATE UserTags SET clickCount = ? WHERE username = ? AND tag = ?");
            preparedStatement.setInt(1, userTag.getClickCount());
            preparedStatement.setString(2, userTag.getUsername());
            preparedStatement.setString(3, userTag.getTag());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserTag getUserTagByUsernameAndTag(String username, String tag) {
        try {
            var preparedStatement = connection.prepareStatement("SELECT * FROM UserTags WHERE username = ? AND tag = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, tag);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var userTag = new UserTag();
                userTag.setUsername(resultSet.getString("username"));
                userTag.setTag(resultSet.getString("tag"));
                userTag.setClickCount(resultSet.getInt("clickCount"));
                return userTag;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
