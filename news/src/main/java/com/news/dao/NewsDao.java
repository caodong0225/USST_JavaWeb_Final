package com.news.dao;

import com.news.model.News;

import java.util.List;

public interface NewsDao {
    List<News> getTopNewsList(int num);
    List<News> getNewsList(String zone,int num);
}
