package com.neu.mapper;

import com.neu.entity.Article;
import com.neu.entity.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ArticleMapper {
    Article selectOneArticleById(Integer articleId);

    Integer insertOneArticle(Article article);

    List<ArticleComment> selectCommentByArticleIdAndPage(@Param("articleId") Integer articleId, @Param("startPage")int startPage, @Param("endPage")int endPage);

    Boolean updateArticle(Article article);

    Boolean deleteArticleById(Integer articleId);
}
