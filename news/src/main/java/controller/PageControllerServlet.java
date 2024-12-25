package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class PageControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        System.out.println("访问路径: " + path);
        if (path.endsWith("/login")) {
            req.getRequestDispatcher("WEB-INF/loginPage.jsp").forward(req, resp);
        } else if (path.endsWith("/register")) {
            req.getRequestDispatcher("WEB-INF/registerPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/mainPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
