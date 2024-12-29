package com.news.filter;

import com.news.Logger;
import com.news.util.EncodeUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "UserDataFilter", urlPatterns = {"/api/login", "/api/register"})
// 处理前端加密后传输的用户名和密码
public class UserDataFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var userName = servletRequest.getParameter("username");
        var password = servletRequest.getParameter("password");
        Logger.log("UserDataFilter: 输入内容 userName = " + userName + ", password = " + password);
        var map = servletRequest.getParameterMap();
        Logger.log("UserDataFilter: map.size = " + map.size());
        for (var key : map.keySet()) {
            Logger.log("UserDataFilter: key = " + key + ", value = " + map.get(key)[0]);
        }
        if (password == null || password.isEmpty()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        userName = EncodeUtil.decodeTest(userName);
        password = EncodeUtil.decodeTest(password);
        servletRequest.setAttribute("userName", userName);
        servletRequest.setAttribute("password", password);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
