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
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Logger.log("SearchServlet: 搜索新闻api调用");
        String searchText = req.getParameter("search");
        if (searchText.isBlank()) {
            Logger.log("SearchServlet: 搜索新闻api调用失败，参数为空或参数只有空格");
            return;
        }

        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        var out = resp.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new HashMap<String, Object>();

        for (var news : NewsService.getInstance().getAllNewsList()) {
            if (news.getTitle().contains(searchText)) {
                data.put(news.getId(), news);
            }
        }
        result.put("data", data);
        Logger.log("SearchServlet: 搜索新闻api调用成功，结果：" + new ObjectMapper().writeValueAsString(result));
        out.println(new ObjectMapper().writeValueAsString(result));
    }
}
