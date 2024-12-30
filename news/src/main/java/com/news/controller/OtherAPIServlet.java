package com.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = {"/api/getUserName"})
public class OtherAPIServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var url = request.getRequestURI();
        if (url.endsWith("/api/getUserName")) {
            getUserName(request, response);
        }
        else {
            response.setStatus(404);
        }
    }
    public void getUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.log("OtherAPIServlet: 获取用户名api调用");
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
