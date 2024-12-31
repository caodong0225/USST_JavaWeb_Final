package com.news.service;

import com.news.dao.NewsDao;
import com.news.dao.impl.FakeNewsDao;
import com.news.model.News;

import java.util.List;

public class NewsService {
    private FakeNewsDao newsDao;
    private static NewsService instance;
    public static NewsService getInstance() {
        if (instance == null) {
            instance = new NewsService();
            instance.init();
        }
        return instance;
    }
    private void init() {

        newsDao = new FakeNewsDao();
    }
    public void initList(String path)
    {
        newsDao.initList(path);
    }

    public List<News> getTopNewsList(int num) {
        return newsDao.getTopNewsList(num);
    }

    public List<News> getNewsList(String zone,int num) {
        return newsDao.getNewsList(zone,num);
    }

}
