package usst.web.mapper;

import org.apache.ibatis.annotations.*;
import usst.web.pojo.Article;

public interface ArticleMapper {

    @Insert("INSERT INTO article(title, author, content) VALUES(#{title}, #{author}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertArticle(Article article);

    @Select("SELECT * FROM article WHERE id=#{id}")
    Article getArticleById(int id);
}
