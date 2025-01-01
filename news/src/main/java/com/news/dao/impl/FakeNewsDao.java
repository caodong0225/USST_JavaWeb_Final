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
import java.util.Random;

public class FakeNewsDao implements NewsDao {
    private HashMap<String, News> newsMap = new HashMap<>();
    private HashMap<String,List<News>> newsZoneMap=new HashMap<>();
    private Random random = new Random();
    public FakeNewsDao() {
        init();
    }
    private void init() {
    }
    public void initList(String path)
    {

        var files=listFilesInDirectory(path);
        for (var file : files) {
            if (file.isFile()&&file.getName().endsWith(".json")) {
                var news = loadNewsFromFile(file);
                if (news != null) {
                    newsMap.put(news.getId(), news);
                    news.initZone();
                    if(newsZoneMap.containsKey(news.getZone()))
                    {
                        newsZoneMap.get(news.getZone()).add(news);
                    }
                    else
                    {
                        var list=new ArrayList<News>();
                        list.add(news);
                        newsZoneMap.put(news.getZone(),list);
                    }
                }
            }
        }
        Logger.log("FakeNewsDao: 初始化完成");
        for (var key : newsZoneMap.keySet()) {
            Logger.log("FakeNewsDao: 分区"+key+"共"+newsZoneMap.get(key).size()+"条新闻");
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
        var keys = new ArrayList<>(newsMap.keySet());
        var list = new ArrayList<News>();
        for (int i = 0; i < num; i++) {
            var key = keys.get(random.nextInt(keys.size()));
            list.add(newsMap.get(key));
            keys.remove(key);
        }
        return list;
    }

    @Override
    public List<News> getNewsByZoneList(String zone,int num) {
        var result = new ArrayList<News>();
        if (zone.equals("全部")) {
            return getTopNewsList(num);
        }
        if(newsZoneMap.containsKey(zone))
        {
            var list=new ArrayList<>(newsZoneMap.get(zone));
            for (int i = 0; i < num; i++) {
                var index = random.nextInt(list.size());
                result.add(list.get(index));
                list.remove(index);
                if (list.isEmpty()) {
                    break;
                }
            }
        }
        else {
            Logger.log("FakeNewsDao: 未找到分区"+zone);
            return getTopNewsList(num);
        }
        return result;
    }

    @Override
    public List<News> getAllNewsList() {
        var keys = new ArrayList<>(newsMap.keySet());
        var list = new ArrayList<News>();
        for (var key : keys) {
            list.add(newsMap.get(key));
        }
        return list;
    }

    @Override
    public News getNewsById(int id) {
        if (newsMap.containsKey(id)) {
            return newsMap.get(id);
        }
        return null;
    }

}
