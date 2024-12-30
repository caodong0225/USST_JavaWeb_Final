package com.news.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.model.User;
import com.news.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = {"/register","/api/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var url = req.getRequestURI();
        if (url.endsWith("/api/register"))
        {
            resp.sendError(404);
        }
        else {
            req.getRequestDispatcher("WEB-INF/registerPage.jsp").forward(req,resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("/api/register"))
        {
            doRegisterApiPost(req, resp);
        }
        else {
            resp.sendError(404);
        }
    }
    private void doRegisterApiPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var userName = (String) req.getAttribute("userName");
        var password = (String) req.getAttribute("password");
        Logger.log("RegisterServlet: 输入内容 userName = " + userName+", password = "+password);
        String message = "";
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty())
        {
            message = "用户名或密码不能为空";
        }
        else if(userName.length()>20||password.length()>20) {
            message = "用户名或密码最长为20个字符";
        }
        else if (!userName.matches("^[a-zA-Z0-9_]+$"))
        {
            message = "用户名只能包含字母、数字和下划线";
        }
        else if (!password.matches("^[a-zA-Z0-9_]+$"))
        {
            message = "密码只能包含字母、数字和下划线";
        }
        else if (userName.length()<5 || password.length()<5)
        {
            message = "用户名或密码过短";

        }
        else if(UserService.getInstance().getUserByUsername(userName)!=null) {
            message = "用户名已存在";
        }
        else {
            var user=new User();
            user.setUsername(userName);
            user.setPassword(password);
            if (UserService.getInstance().addUser(user))
            {
                message = "注册成功";
            }
            else {
                message = "注册失败";
            }
        }
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("message", message);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new ObjectMapper().writeValueAsString(jsonMap));
        out.flush();
    }
}
