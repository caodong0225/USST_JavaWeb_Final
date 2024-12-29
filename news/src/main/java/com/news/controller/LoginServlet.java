package com.news.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
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

@WebServlet(name = "LoginServlet", value = {"/login","/api/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var url = req.getRequestURI();
        if (url.endsWith("/api/login"))
        {
            resp.sendError(404);
        }
        else {
            req.getRequestDispatcher("WEB-INF/loginPage.jsp").forward(req,resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().endsWith("/api/login"))
        {
            doLoginApiPost(req, resp);
        }
        else {
            resp.sendError(404);
        }
    }
    private void doLoginApiPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var userName = (String) req.getAttribute("userName");
        var password = (String) req.getAttribute("password");
        Logger.log("LoginServlet: 输入内容 userName = " + userName+", password = "+password);
        var session = req.getSession();
        if (userService.checkUser(userName, password))
        {
            session.setAttribute("userName", userName);
            resp.sendRedirect("mainPage");
        }
        else
        {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("message", "用户名或密码错误");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(new ObjectMapper().writeValueAsString(jsonMap));
            out.flush();
        }
    }
}
