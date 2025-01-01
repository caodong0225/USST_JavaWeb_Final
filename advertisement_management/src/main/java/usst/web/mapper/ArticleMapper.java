package usst.web.mapper;

import org.apache.ibatis.annotations.*;
import usst.web.pojo.Article;

public interface ArticleMapper {

    @Insert("INSERT INTO article(ad_id, title, author, content) VALUES(#{ad_id}, #{title}, #{author}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertArticle(Article article);

    // 更新文章
    @Update("UPDATE article SET ad_id = #{ad_id}, title = #{title}, author = #{author}, content = #{content} WHERE id = #{id}")
    int updateArticle(Article article);

    @Select("SELECT * FROM article WHERE id=#{id}")
    Article getArticleById(int id);
}
