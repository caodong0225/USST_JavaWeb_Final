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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = {"/register", "/api/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var url = req.getRequestURI();
        if (url.endsWith("/api/register")) {
            resp.sendError(404);
        } else {
            req.getRequestDispatcher("WEB-INF/registerPage.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().endsWith("/api/register")) {
            doRegisterApiPost(req, resp);
        } else {
            resp.sendError(404);
        }
    }

    private void doRegisterApiPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var userName = (String) req.getAttribute("userName");
        var password = (String) req.getAttribute("password");
        var birthday = req.getParameter("birthday");
        var sex = req.getParameter("sex");
        var career = req.getParameter("career");
        var country = req.getParameter("country");
        var education = req.getParameter("education");
        var simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate;
        boolean success = false;
        try {
            birthdayDate = simpleDateFormat.parse(birthday);
        } catch (Exception e) {
            e.printStackTrace();
            birthdayDate = null;
        }
        Logger.log("RegisterServlet: 输入内容 userName = " + userName + ", password = " + password + "birthday = " + birthday + "sex=" + sex + "career=" + career + "country=" + country + "education=" + education);
        String message = "";
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            message = "用户名或密码不能为空";
        } else if (userName.length() > 20 || password.length() > 20) {
            message = "用户名或密码最长为20个字符";
        } else if (!userName.matches("^[a-zA-Z0-9_]+$")) {
            message = "用户名只能包含字母、数字和下划线";
        } else if (!password.matches("^[a-zA-Z0-9_]+$")) {
            message = "密码只能包含字母、数字和下划线";
        } else if (userName.length() < 5 || password.length() < 5) {
            message = "用户名或密码过短";

        } else if (UserService.getInstance().getUserByUsername(userName) != null) {
            message = "用户名已存在";
        } else if (birthday == null || birthday.isEmpty()) {
            message = "生日不能为空";

        } else if (sex == null || sex.isEmpty()) {
            message = "性别不能为空";
        } else if (career == null || career.isEmpty()) {
            message = "职业不能为空";
        } else if (country == null || country.isEmpty()) {
            message = "国籍不能为空";
        } else if (education == null || education.isEmpty()) {
            message = "学历不能为空";
        } else if (birthdayDate == null) {
            message = "生日格式错误";
        } else if (new Date().before(birthdayDate)) {
            message = "生日不能在未来";
        } else {
            var user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            user.setSex(sex);
            user.setBirthday(birthdayDate);
            user.setCareer(career);
            user.setCountry(country);
            user.setEducationBackground(education);
            if (UserService.getInstance().addUser(user)) {
                message = "注册成功";
                success = true;

            } else {
                message = "注册失败";
            }
        }
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("message", message);
        jsonMap.put("success", success);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new ObjectMapper().writeValueAsString(jsonMap));
        out.flush();
    }
}
