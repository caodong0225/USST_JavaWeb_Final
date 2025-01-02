package com.news.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News {
    private String id;
    private String title;
    private String content;
    private String author;
    private String cover;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;
    private List<String> tags;
    private String zone;

    public News(String title, String content, String author, String cover, Date date, List<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.cover = cover;
        this.date = date;
        this.tags = tags;

        if (!tags.isEmpty()) {
            this.zone = tags.get(0);
        } else {
            this.zone = "生活";
        }
    }

    public News() {

    }

    public void initZone() {
        if (!tags.isEmpty()) {
            this.zone = tags.get(0);
        } else {
            this.zone = "全部";
        }
    }

    public String setId(String id) {
        return this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }
    public boolean isAvaliable() {
        return !(getDate() == null || getContent().isEmpty() || getAuthor() == null);
    }
}
