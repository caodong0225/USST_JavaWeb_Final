package usst.web.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usst.web.mapper.ArticleMapper;
import usst.web.pojo.Article;

@Service
public class ArticleServiceImpl {

    @Autowired
    private ArticleMapper articleMapper;
    @Resource
    private usst.web.mq.RocketMQProducerService RocketMQProducerService;

    public boolean publishArticle(Article article) {
        int res = articleMapper.insertArticle(article);
        System.out.println(article.getId());
        try {
            RocketMQProducerService.sendMessage("testTopic",article.getId() + "&&&&" + article.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res > 0;
    }

    public boolean updateArticle(Article article) {
        int res = articleMapper.updateArticle(article);
        return res > 0;
    }

    public Article getArticleById(int id) {
        return articleMapper.getArticleById(id);
    }
}