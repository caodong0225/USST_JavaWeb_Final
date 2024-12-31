package com.news.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.service.NewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/searchPage.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Logger.log("GetNewsListServlet: 获取新闻列表api调用");
        var session = req.getSession();
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        var out = resp.getWriter();
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
}
