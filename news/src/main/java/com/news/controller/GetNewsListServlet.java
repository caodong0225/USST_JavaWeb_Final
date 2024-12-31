package com.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.service.NewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = {"/api/getNewsList", "/api/getTopNewsList"})
public class GetNewsListServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        Logger.log("GetNewsListServlet初始化");
        try {
            var dir = "WEB-INF/classes/NewsJson";
            NewsService.getInstance().initList(getServletContext().getRealPath(dir));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().endsWith("/api/getNewsList")) {
            getNewsList(request, response);
        } else if (request.getRequestURI().endsWith("/api/getTopNewsList")) {
            getTopNewsList(request, response);
        }
    }
    private void getTopNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Logger.log("GetNewsListServlet: 获取新闻列表api调用");
        var session = request.getSession();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new HashMap<String, Object>();
        var topList = NewsService.getInstance().getTopNewsList(5);
        Logger.log("获取到置顶新闻列表："+topList.size());
        for (var news : topList) {
            news.setCover("img/news/"+news.getCover()+".jpg");
            data.put(news.getId(), news);
        }
        result.put("data", data);
        Logger.log("GetNewsListServlet: 获取新闻列表api调用成功，结果："+new ObjectMapper().writeValueAsString(result));
        out.println(new ObjectMapper().writeValueAsString(result));
    }
    private void getNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.log("GetNewsListServlet: 获取新闻列表api调用");
        var session = request.getSession();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new HashMap<String, Object>();
        var topList = NewsService.getInstance().getTopNewsList(5);
        for (var news : topList) {
            news.setCover("img/news/"+news.getCover()+".jpg");
            data.put(news.getId(), news);
        }
        result.put("data", data);
        out.println(new ObjectMapper().writeValueAsString(result));
    }
}
