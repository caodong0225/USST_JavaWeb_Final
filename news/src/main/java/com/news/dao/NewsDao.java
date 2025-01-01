package com.news.dao;

import com.news.model.News;

import java.util.List;

public interface NewsDao {
    List<News> getTopNewsList(int num);
    List<News> getNewsByZoneList(String zone,int num);
    List<News> getAllNewsList();
    News getNewsById(int id);
}
