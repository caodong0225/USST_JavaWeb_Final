package com.news.controller;

import com.news.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(value = {"/api/getImage"})
public class GetImageServlet extends HttpServlet {
    private final HashMap<String, String> imageMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        var dir = new File(getServletContext().getRealPath("img/news/"));
        Logger.log("GetImageServlet: 初始化" + dir.getPath());
        if (dir.isDirectory()) {
            var files = dir.listFiles();
            for (var file : files) {
                if (!file.isFile()) {
                    continue;
                }
                var fileName = file.getName().split("\\.")[0];
//                Logger.log("GetImageServlet: 读取到图片文件" + fileName);
                imageMap.put(fileName, file.getPath());
            }
        }
        imageMap.put("undefined", getServletContext().getRealPath("img/undefined.png"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var imageUrl = request.getParameter("imageUrl");
        Logger.log("GetImageServlet: 获取图片api调用，图片地址：" + imageUrl);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        var out = response.getOutputStream();
        if (imageMap.containsKey(imageUrl)) {
            var fis = new FileInputStream(imageMap.get(imageUrl));
            var buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            fis.close();
        } else {
            var fis = new FileInputStream(imageMap.get("undefined"));
            var buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            fis.close();
        }
        out.flush();
    }
}
