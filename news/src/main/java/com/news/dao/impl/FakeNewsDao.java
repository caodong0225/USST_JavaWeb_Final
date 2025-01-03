package com.news.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.model.News;
import com.news.service.NewsService;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FakeNewsDao {
    public static void initDb(String path) {
        var files = listFilesInDirectory(path);
        for (var file : files) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                var news = loadNewsFromFile(file);
                if (news != null) {
                    news.initZone();
                    if (!news.isAvaliable()) {
                        continue;
                    }
                    NewsService.getInstance().addNews(news);
                }
            }
        }
    }

    private static News loadNewsFromFile(File file) {
        try {
            var fis = new FileInputStream(file);
            var buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();
            var content = new String(buffer, StandardCharsets.UTF_8);
            var news = new ObjectMapper().readValue(content, News.class);
            news.setId(file.getName().replace(".json", ""));
            return news;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<File> listFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        Logger.log(directory.getPath());
        File[] files = directory.listFiles(); // 获取文件夹下的所有文件和文件夹

        List<File> fileList = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file); // 添加文件到列表
                }
                // 如果需要递归遍历子文件夹，可以在这里添加代码
            }
        }
        return fileList;
    }

    public static void main(String[] args) {
        //path填包含新闻json的文件夹目录
        var path = "D:\\学习\\web开发\\USST_JavaWeb_ADTool\\news\\src\\main\\resources\\NewsJson\\";
        initDb(path);
    }
}
