package com.news.dao;

import com.news.model.News;

import java.util.List;

public interface NewsDao {
    List<News> getTopNewsList(int num);

    List<News> getNewsByZoneList(String zone, int num);

    News getNewsById(String id);

    boolean addNews(News news);

    boolean deleteNews(String id);

    List<News> getNewsBysearch(String text, int top);
}
