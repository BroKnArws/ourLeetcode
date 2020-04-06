package com.neu.service;

import com.neu.dto.request.EditArticleRequest;
import com.neu.dto.response.ArticleDetail;
import com.neu.dto.response.ArticlePreview;
import com.neu.entity.Article;
import com.neu.entity.LeetcodeUser;
import com.neu.mapper.ArticleMapper;
import com.neu.mapper.CircleMapper;
import com.neu.mapper.UserMapper;
import com.neu.vo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {


    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;




    public List<ArticlePreview> getArticlePreviewList(String userId, Integer startPage, Integer endPage) {


        List<ArticlePreview> articlePreviews = circleMapper.selectArticlePreviewsByPage(userId,startPage,endPage);


        return articlePreviews;
    }


    public Integer addOneArticle(Article article) {

        articleMapper.insertOneArticle(article);

        Integer newId = article.getId();

        return newId;
    }

    public ArticleDetail getArticleDetail(Integer articleId) {


        ArticleDetail articleDetail = new ArticleDetail();
        //获取文章信息
        Article article = articleMapper.selectOneArticleById(articleId);
        //获取作者信息
        LeetcodeUser user = userMapper.selectUserById(article.getCreatorId());
        Author author = new Author(user);


        ////封装返回数据
        articleDetail.setArticle(article);
        articleDetail.setAuthor(author);


        return articleDetail;
    }


    public List<ArticlePreview> getMyArticleList(Integer authorId, Integer startPage, Integer endPage) {

        List<ArticlePreview> articlePreviews = circleMapper.selectArticlePreviewsByAuthorIdAndPage(authorId,startPage,endPage);

        return articlePreviews;
    }



    public Boolean putOneArticle(Article article) {


        Boolean hasUpdate = articleMapper.updateArticle(article);

        return hasUpdate;
    }

    public Boolean deleteOneArticle(Integer articleId) {
        Boolean hasDelete = articleMapper.deleteArticleById(articleId);
        return hasDelete;
    }


    public List<ArticlePreview> getArticlePreviewListByTopicId(String userId, Integer topicId, int pageStart, int pageEnd) {

        List<ArticlePreview> articlePreviews = circleMapper.selectArticlePreviewsByTopicIdAndPage(userId,topicId,pageStart,pageEnd);

        return articlePreviews;
    }


    public Article getById(Integer id) {
        Article article = articleMapper.selectOneArticleById(id);
        return article;
    }
}
