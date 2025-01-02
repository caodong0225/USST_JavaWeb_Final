package com.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.service.NewsCrawlerService;
import com.news.service.NewsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(value = {"/api/getNewsList", "/api/getTopNewsList", "/api/getNewsZoneList"})
public class GetNewsListServlet extends HttpServlet {
    Timer timer;
    @Override
    public void init() {
        timer=new Timer();
        var calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        long delay =calendar.getTimeInMillis() - System.currentTimeMillis();
        if (delay < 0) {
            // 如果计算的时间已经过去，则设置为第二天的同一时间
            calendar.add(Calendar.DATE, 1);
            delay = calendar.getTimeInMillis() - System.currentTimeMillis();
        }
        var path=getServletContext().getRealPath("img/news/");
        Logger.log("GetNewsListServlet: 初始化，设置定时任务，每天执行一次，时间："+new Date(calendar.getTimeInMillis())+"，图片保存地址："+path);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行爬虫任务");
                try {
                    NewsCrawlerService.autoCraw(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().endsWith("/api/getNewsList")) {
            getNewsList(request, response);
        } else if (request.getRequestURI().endsWith("/api/getTopNewsList")) {
            getTopNewsList(request, response);
        } else if (request.getRequestURI().endsWith("/api/getNewsZoneList")) {
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
        Logger.log("GetNewsListServlet: 获取新闻列表api调用，获取分区：" + zone);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new HashMap<String, Object>();
        var newsList = NewsService.getInstance().getNewsByZoneList(zone, 5);
        for (var news : newsList) {
            data.put(news.getId(), news);
        }
        result.put("data", data);
//        Logger.log("GetNewsListServlet: 获取新闻列表api调用成功，结果："+new ObjectMapper().writeValueAsString(result));
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
