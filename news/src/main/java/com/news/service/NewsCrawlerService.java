package com.news.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.Logger;
import com.news.model.News;
import com.news.util.CrawUtil;
import com.news.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsCrawlerService {
    public static void crawNewsList(String savePath, String url, String zone) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsList = doc.getElementsByClass("tright");
            for (Element news : newsList) {
                String newsUrl = news.select("span").attr("lanmu1");
                Document newsDoc = Jsoup.connect(newsUrl).get();
                Thread.sleep(500);
                var title = CrawUtil.getNewsTitle(newsDoc);
                var cover = news.select("img").attr("src");
                var time = CrawUtil.getNewsTime(newsDoc);
                var author = CrawUtil.getAuthor(newsDoc);
                var content = CrawUtil.getNewsContent(newsDoc);
                var newsId = newsUrl.substring(newsUrl.lastIndexOf("/") + 1, newsUrl.lastIndexOf("."));
                saveToDataBase(savePath, newsId, title, time, content, cover, author, zone);

                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //已经弃用，保存到本地
//    public static void SaveNews(String savePath, String newsId, String title, Date time, String content, String cover, String author, String zone) {
//        var news = new News();
//        news.setTitle(title);
//        news.setDate(time);
//        news.setContent(content);
//        news.setCover(newsId);
//        news.setId(newsId);
//        news.setAuthor(author);
//        news.setZone(zone);
//        news.setTags(new ArrayList<>(List.of(zone)));
//        if (!news.isAvaliable()) {
//            Logger.log("新闻不可用，已跳过！"+title);
//            return;
//        }
//        try {
//            var json = new ObjectMapper().writeValueAsString(news);
//            var path = savePath + "\\json\\" + newsId + ".json";
//            Logger.log("保存新闻到：" + path);
//            var file = new File(path);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            var out = new FileOutputStream(file);
//            out.write(json.getBytes());
//            out.close();
//            CrawUtil.downloadImage(cover, savePath + "\\" + newsId + ".jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void saveToDataBase(String picSavePath, String newsId, String title, Date time, String content, String cover, String author, String zone)
    {
        var news = new News();
        news.setTitle(title);
        news.setDate(time);
        news.setContent(content);
        news.setCover(newsId);
        news.setId(newsId);
        news.setAuthor(author);
        news.setZone(zone);
        news.setTags(new ArrayList<>(List.of(zone)));
        if (!news.isAvaliable()) {
            return;
        }
        if (NewsService.getInstance().addNews(news))
        {
            Logger.log("保存新闻成功！"+title);
            CrawUtil.downloadImage(cover, picSavePath + "\\" + newsId + ".jpg");
        }
        else {
            Logger.log("保存新闻失败！"+title);
        }
    }

    public static void main(String[] args) {

        var data = new ArrayList<String>();
        data.add("时尚");
        data.add("艺术");
        data.add("娱乐");
        data.add("教育");
        data.add("宠物");
        data.add("环保");
        data.add("气象");
        data.add("科技");
        data.add("政治");
        data.add("经济");
        var path="D:\\学习\\web开发\\USST_JavaWeb_ADTool\\news\\src\\main\\webapp\\img\\news";
        for (var zone : data) {
            crawlNewsByZone(path, zone, 1);
        }
    }
    public static void  autoCraw(String imgSavePath)
    {
        var data = new ArrayList<String>();
        data.add("时尚");
        data.add("艺术");
        data.add("娱乐");
        data.add("教育");
        data.add("宠物");
        data.add("环保");
        data.add("气象");
        data.add("科技");
        data.add("政治");
        data.add("经济");
        for (var zone : data) {
            crawlNewsByZone(imgSavePath, zone, 1);
        }
    }

    public static void crawlNewsByZone(String savePath, String zone, int pageNum) {;
        for (int i = 1; i <= pageNum; i++) {
            var url = "https://search.cctv.com/search.php?qtext=" + zone + "&sort=relevance&type=web&vtime=&datepid=1&channel=&page=" + i;
            NewsCrawlerService.crawNewsList(savePath, url, zone);
        }
    }

}
