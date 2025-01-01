package com.news.dao.impl;

import com.news.dao.NewsDao;
import com.news.model.News;
import com.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    private Connection connection;

    public NewsDaoImpl() {
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
    public List<News> getTopNewsList(int num) {
        var sql = "SELECT TOP " + num + " * FROM News ORDER BY NEWID()";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            var resultSet = preparedStatement.executeQuery();
            var newsList = new ArrayList<News>();
            while (resultSet.next()) {
                var news = new News();
                news.setId(resultSet.getString("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setAuthor(resultSet.getString("author"));
                news.setCover(resultSet.getString("cover"));
                news.setDate(resultSet.getDate("date"));
                news.setTags(List.of(resultSet.getString("tags").split(",")));
                news.setZone(resultSet.getString("zone"));
                newsList.add(news);
            }
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<News> getNewsByZoneList(String zone, int num) {
        if (zone.equals("全部")) {
            return getTopNewsList(num);
        }
        var sql = "SELECT TOP " + num + " * FROM News WHERE zone = ? ORDER BY NEWID()";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, zone);
            var resultSet = preparedStatement.executeQuery();
            var newsList = new ArrayList<News>();
            while (resultSet.next()) {
                var news = new News();
                news.setId(resultSet.getString("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setAuthor(resultSet.getString("author"));
                news.setCover(resultSet.getString("cover"));
                news.setDate(resultSet.getDate("date"));
                news.setTags(List.of(resultSet.getString("tags").split(",")));
                news.setZone(resultSet.getString("zone"));
                newsList.add(news);
            }
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public News getNewsById(String id) {
        var sql = "SELECT * FROM News WHERE id = ?";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var news = new News();
                news.setId(resultSet.getString("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setAuthor(resultSet.getString("author"));
                news.setCover(resultSet.getString("cover"));
                news.setDate(resultSet.getDate("date"));
                news.setTags(List.of(resultSet.getString("tags").split(",")));
                news.setZone(resultSet.getString("zone"));
                return news;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNews(News news) {
        if (getNewsById(news.getId()) != null) {
            return false;
        }
        var sql = "INSERT INTO News (id,title, content, author, cover, date, tags, zone) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, news.getId());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setString(4, news.getAuthor());
            preparedStatement.setString(5, news.getCover());
            preparedStatement.setDate(6, new java.sql.Date(news.getDate().getTime()));
            preparedStatement.setString(7, String.join(",", news.getTags()));
            preparedStatement.setString(8, news.getZone());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNews(String id) {
        var sql = "DELETE FROM News WHERE id = ?";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<News> getNewsBysearch(String text, int top) {
        var sql = "SELECT TOP " + top + " * FROM News WHERE title LIKE '%" + text + "%'OR content LIKE '%" + text + "%'";
        try {
            var preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, text);
            var resultSet = preparedStatement.executeQuery();
            var newsList = new ArrayList<News>();
            while (resultSet.next()) {
                var news = new News();
                news.setId(resultSet.getString("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setAuthor(resultSet.getString("author"));
                news.setCover(resultSet.getString("cover"));
                news.setDate(resultSet.getDate("date"));
                news.setTags(List.of(resultSet.getString("tags").split(",")));
                news.setZone(resultSet.getString("zone"));
                newsList.add(news);
            }
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
