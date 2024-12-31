package com.news.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.dao.NewsDao;
import com.news.model.News;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeNewsDao implements NewsDao {
    private HashMap<String, News> newsMap = new HashMap<>();
    public FakeNewsDao() {
        init();
    }
    private void init() {
        Logger.log("FakeNewsDao: 初始化");
        var path = "WEB-INF/classes/News_json/";
        var files=listFilesInDirectory(path);
        for (var file : files) {
            Logger.log("FakeNewsDao: 读取文件" + file.getName());
        }
    }
    public void initList(String path)
    {
        var files=listFilesInDirectory(path);
        for (var file : files) {
            if (file.isFile()&&file.getName().endsWith(".json")) {
                var news = loadNewsFromFile(file);
                if (news != null) {
                    newsMap.put(news.getId(), news);
                }

            }
        }
    }
    private News loadNewsFromFile(File file) {
        try {
            var fis = new FileInputStream(file);
            var buffer = new byte[(int) file.length()];
            fis.read(buffer);
            fis.close();
            var content = new String(buffer, "UTF-8");
            var news = new ObjectMapper().readValue(content, News.class);
            news.setId(file.getName().replace(".json", ""));
            return news;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private List<File> listFilesInDirectory(String directoryPath) {
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
    @Override
    public List<News> getTopNewsList(int num) {
        var list = new ArrayList<News>();
        for (var entry : newsMap.entrySet()) {
            if (list.size() >= num) {
                break;
            }
            list.add(entry.getValue());
        }
        return list;
    }

    @Override
    public List<News> getNewsList(String zone,int num) {
        return List.of();
    }
}
