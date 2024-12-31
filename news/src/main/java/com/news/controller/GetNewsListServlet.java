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
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(value = {"/api/getNewsList", "/api/getTopNewsList", "/api/getNewsZoneList"})
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
        else if (request.getRequestURI().endsWith("/api/getNewsZoneList")) {
            getNewsZoneList(request, response);
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
        for (var news : topList) {
            data.put(news.getId(), news);
        }
        result.put("data", data);
//        Logger.log("GetNewsListServlet: 获取新闻列表api调用成功，结果："+new ObjectMapper().writeValueAsString(result));
        out.println(new ObjectMapper().writeValueAsString(result));
    }
    private void getNewsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var session = request.getSession();
        var zone = request.getParameter("zone");
        Logger.log("GetNewsListServlet: 获取新闻列表api调用，获取分区："+zone);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new HashMap<String, Object>();
        var newsList = NewsService.getInstance().getNewsList(zone,5);
        for (var news : newsList) {
            data.put(news.getId(), news);
        }
        result.put("data", data);
        Logger.log("GetNewsListServlet: 获取新闻列表api调用成功，结果："+new ObjectMapper().writeValueAsString(result));
        out.println(new ObjectMapper().writeValueAsString(result));
    }
    private void getNewsZoneList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.log("GetNewsListServlet: 获取新闻分区api调用");
        var session = request.getSession();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new ArrayList<String>();
        data.add("全部");
        data.add("时尚");
        data.add("艺术");
        data.add("娱乐");
        data.add("教育");
        data.add("宠物");
        data.add("环保");
        data.add("气象");
        data.add("科技");
        data.add("政治");
        data.add("经济");
        result.put("data", data);
        out.println(new ObjectMapper().writeValueAsString(result));
    }
}
