package com.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.model.User;
import com.news.service.UserService;
import com.news.util.EncodeUtil;
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
            data.put("userName", username);
            User user = UserService.getInstance().getUserByUsername(username);
            data.put("age", user.getAge()+"");
            data.put("gender", user.getSex());
            data.put("occupation", user.getCareer());
            data.put("education_level", user.getEducationBackground());
            data.put("region", user.getRegion());
            data.put("country", user.getCountry());
            result.put("isLogin", true);
        } else {
            data.put("userName", request.getRemoteAddr());
            data.put("age", null);
            data.put("gender", null);
            data.put("occupation", null);
            data.put("education_level", null);
            data.put("region", null);
            data.put("country", null);
            result.put("isLogin", false);
        }
        result.put("data", data);
        out.println(new ObjectMapper().writeValueAsString(result));
    }
}
