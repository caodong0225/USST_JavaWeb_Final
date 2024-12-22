package usst.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import usst.web.mapper.ArticleMapper;
import usst.web.pojo.Article;

public interface ArticleService {
    public boolean publishArticle(Article article);
    public Article getArticleById(int id);
}
