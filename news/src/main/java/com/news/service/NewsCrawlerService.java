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

    public static void crawlNews(String url) {
        try {
            // 这里以一个示例URL代替，你需要替换成合法的目标URL
            Document doc = Jsoup.connect(url).get();
            Logger.log("新闻标题：" + CrawUtil.getNewsTitle(doc));
            Logger.log("新闻时间：" + DateUtil.ChangeToString(CrawUtil.getNewsTime(doc)));
            Logger.log("新闻内容：" + CrawUtil.getNewsContent(doc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void crawNewsList(String savePath, String url, String zone) {
        try {
            // 这里以一个示例URL代替，你需要替换成合法的目标URL
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
                SaveNews(savePath, newsId, title, time, content, cover, author, zone);

                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SaveNews(String savePath, String newsId, String title, Date time, String content, String cover, String author, String zone) {
        var news = new News();
        news.setTitle(title);
        news.setDate(time);
        news.setContent(content);
        news.setCover(newsId);
        news.setId(newsId);
        news.setAuthor(author);
        news.setZone(zone);
        news.setTags(new ArrayList<>(List.of(zone)));
        try {
            var json = new ObjectMapper().writeValueAsString(news);
            var path = savePath + "\\json\\" + newsId + ".json";
            Logger.log("保存新闻到：" + path);
            var file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            var out = new FileOutputStream(file);
            out.write(json.getBytes());
            out.close();
            CrawUtil.downloadImage(cover, savePath + "\\" + newsId + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        var data = new ArrayList<String>();
        data.add("艺术");
        data.add("娱乐");
        data.add("教育");
        data.add("宠物");
        data.add("环保");
        data.add("科技");
        data.add("政治");
        data.add("经济");
        for (var zone : data) {
            crawlNewsByZone("D:\\yjj\\爬虫结果", zone, 3);
        }
    }

    public static void crawlNewsByZone(String savePath, String zone, int pageNum) {
        for (int i = 1; i <= pageNum; i++) {
            var url = "https://search.cctv.com/search.php?qtext=" + zone + "&sort=relevance&type=web&vtime=&datepid=1&channel=&page=" + i;
            NewsCrawlerService.crawNewsList(savePath, url, zone);
        }
    }

}
