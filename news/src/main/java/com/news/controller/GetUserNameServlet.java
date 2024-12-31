package com.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.service.NewsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(value = {"/api/getUserName"})
public class GetUserNameServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.log("GetUserNameServlet: 获取用户名api调用");
        var session = request.getSession();
        var username = session.getAttribute("username");
        if (username == null) {
            username = "";
        }
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("username", username);
        result.put("success", true);
        out.println(new ObjectMapper().writeValueAsString(result));
    }
}
