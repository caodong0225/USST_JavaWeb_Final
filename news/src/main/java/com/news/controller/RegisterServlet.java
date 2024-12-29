package com.news.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
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
        System.out.println("RegisterServlet: 输入内容 userName = " + userName+", password = "+password);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("message", "没有编写注册逻辑！");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new ObjectMapper().writeValueAsString(jsonMap));
        out.flush();
    }
}
