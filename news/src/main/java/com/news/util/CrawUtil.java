package com.news.util;

import com.news.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawUtil {
    public static String removeUnSafeChar(String unsafeHtml) {
        // 使用Jsoup清理HTML，不保留相对链接
        return Jsoup.clean(unsafeHtml, Safelist.basic());
    }

    public static String getNewsContent(Document html) {
        var bodyElement = html.getElementById("page_body");
        Element contentArea = null;
        if (bodyElement != null) {
            contentArea = bodyElement.getElementById("content_area");
        }
        if (contentArea != null) {
            return contentArea.text().replaceAll(" 　　", "\n");
        }
        return "";
    }

    public static String getNewsTitle(Document html) {
        var bodyElement = html.getElementById("page_body");
        if (bodyElement == null) {
            return "";
        }
        Element titleElement = bodyElement.getElementById("title_area");
        if (titleElement == null) {
            return "";
        }
        Elements titleP = titleElement.select("h1");
        return titleP.text();
    }

    public static Date getNewsTime(Document html) {
        var bodyElement = html.getElementById("page_body");
        if (bodyElement == null) {
            return null;
        }
        Element timeElement = bodyElement.getElementById("title_area");
        if (timeElement == null) {
            return null;
        }
        Elements timeP = timeElement.getElementsByClass("info");
        if (timeP.size() == 0) {
            return null;
        }
        String timeStr = timeP.get(0).text();
        return getTime(timeStr);
    }

    private static Date getTime(String timeStr) {
        String regex = "\\d{4}年\\d{1,2}月\\d{1,2}日";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timeStr);

        if (matcher.find()) {
            // 匹配到的时间字符串
            String dateTimeStr = matcher.group();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            try {
                return sdf.parse(dateTimeStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        regex = "\\d{4}-\\d{1,2}-\\d{1,2}";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(timeStr);
        if (matcher.find()) {
            // 匹配到的时间字符串
            String dateTimeStr = matcher.group();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(dateTimeStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Logger.log("时间解析失败：" + timeStr);
        return null;

    }

    public static String getAuthor(Document html) {
        var bodyElement = html.getElementById("page_body");
        if (bodyElement == null) {
            return null;
        }
        Element timeElement = bodyElement.getElementById("title_area");
        if (timeElement == null) {
            return null;
        }
        Elements timeP = timeElement.getElementsByClass("info");
        if (timeP.size() == 0) {
            return null;
        }
        String authorString = timeP.get(0).text();
        String regex = "来源：(\\S+) |";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(authorString);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static String getString(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean downloadImage(String url, String destinationFile) {
        try (InputStream in = new BufferedInputStream(new URL(url).openStream());
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            // 读取数据到buffer中，并写入文件
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("图片下载完成");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
