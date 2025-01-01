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

@WebServlet(name = "NewsDetailServlet", value = "/detail")
public class NewsDetailServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/detailPage.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Logger.log("NewsDetailServlet: 新闻详情api调用");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        var out = resp.getWriter();
        var result = new HashMap<String, Object>();
        var newsInfo = NewsService.getInstance().getNewsById(req.getParameter("newsId"));
        result.put("data", newsInfo);
        if (newsInfo != null) {
            result.put("success", true);
            Logger.log("SearchServlet: 新闻详情api调用成功，结果：" + new ObjectMapper().writeValueAsString(result));
            out.println(new ObjectMapper().writeValueAsString(result));
        } else {
            result.put("success", false);
            Logger.log("SearchServlet: 新闻详情api调用失败，未找到该参数对应的内容：" + req.getParameter("newsId"));
        }

    }
}
