package com.news.service;

import com.news.dao.NewsDao;
import com.news.dao.impl.NewsDaoImpl;
import com.news.model.News;

import java.util.List;

public class NewsService {
    private NewsDao newsDao;
    private static NewsService instance;

    public static NewsService getInstance() {
        if (instance == null) {
            instance = new NewsService();
            instance.init();
        }
        return instance;
    }

    private void init() {

        newsDao = new NewsDaoImpl();
    }

    public List<News> getTopNewsList(int num) {
        return newsDao.getTopNewsList(num);
    }

    public List<News> getNewsByZoneList(String zone, int num) {
        return newsDao.getNewsByZoneList(zone, num);
    }

    public News getNewsById(String id) {
        return newsDao.getNewsById(id);
    }

    public boolean addNews(News news) {
        return newsDao.addNews(news);
    }

    public List<News> searchNewsByTitle(String text) {
        return newsDao.searchNewsByTitle(text,20);
    }
}
