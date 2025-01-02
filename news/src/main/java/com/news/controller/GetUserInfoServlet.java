package com.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.model.User;
import com.news.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

//这段代码为调用广告所用
@WebServlet(value = {"/api/getUserInfo"})
public class GetUserInfoServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger.log("GetUserInfoServlet: 获取用户信息api调用");
        var session = request.getSession();
        String username;
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        var out = response.getWriter();
        var result = new HashMap<String, Object>();
        result.put("success", true);
        var data = new HashMap<String, Object>();
        if (session.getAttribute("username") != null) {
            username = (String) session.getAttribute("username");
            data.put("username", username);
            User user = UserService.getInstance().getUserByUsername(username);
            data.put("age", user.getAge());
            data.put("gender", user.getSex());
            data.put("occupation", user.getCareer());
            data.put("educationLevel", user.getEducationBackground());
            data.put("region", null); //缺地区
            data.put("country", user.getCountry());
        } else {
            //应广告方要求，若用户未登录则给出随机的用户名
            username = UUID.randomUUID().toString();
            data.put("username", username);
            data.put("age", null);
            data.put("gender", null);
            data.put("occupation", null);
            data.put("educationLevel", null);
            data.put("region", null);
            data.put("country", null);
        }
        result.put("data", data);
        out.println(new ObjectMapper().writeValueAsString(result));
    }
}
