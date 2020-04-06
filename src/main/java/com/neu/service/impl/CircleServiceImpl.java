package com.neu.service.impl;

import com.neu.dto.response.ArticleDetail;
import com.neu.dto.response.ArticlePreview;
import com.neu.dto.response.OneArticle;
import com.neu.dto.response.QuestionAnswer;
import com.neu.entity.*;
import com.neu.mapper.ArticleMapper;
import com.neu.mapper.CircleMapper;
import com.neu.mapper.DraftMapper;
import com.neu.mapper.UserMapper;
import com.neu.service.CircleService;
import com.neu.vo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CircleServiceImpl implements CircleService {

    @Autowired
    CircleMapper circleMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DraftMapper draftMapper;


    @Override
    public List<ArticlePreview> getArticlePreviewList(String userId, Integer startPage, Integer endPage) {


        List<ArticlePreview> articlePreviews = circleMapper.selectArticlePreviewsByPage(userId,startPage,endPage);


        return articlePreviews;
    }

    @Override
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

//        OneArticle oneArticle = new OneArticle();
//        //数据绑定
//        oneArticle.setId(article.getId());
//        oneArticle.setTitle(article.getTitle());
//        oneArticle.setThumbnail(article.getThumbnail());
//        oneArticle.setContent(article.getMessage());
//        oneArticle.setUpvote(article.getLikes());
//        oneArticle.setConfused(article.getThinks());
//        oneArticle.setAwesome(article.getSurprises());
//        oneArticle.setCreateAt(article.getInitializeTime());
//        oneArticle.setUpdateAt(article.getEditTime());
//
//        //作者需要去用户表查
//
//        Author author = new Author();//填入信息
////        oneArticle.setAuthor();

        return articleDetail;
    }

    @Override
    public Integer addOneArticle(Article article) {

        articleMapper.insertOneArticle(article);

        Integer newId = article.getId();

        return newId;
    }

    @Override
    public List<ArticlePreview> getMyArticleList(Integer authorId, Integer startPage, Integer endPage) {

        List<ArticlePreview> articlePreviews = circleMapper.selectArticlePreviewsByAuthorIdAndPage(authorId,startPage,endPage);

        return articlePreviews;
    }

    @Override
    public List<ArticleComment> getArticleCommentListByArticleIdAndPage(Integer articleId, int pageStart, int pageEnd) {
        List<ArticleComment> articleComments = articleMapper.selectCommentByArticleIdAndPage(articleId,pageStart,pageEnd);

        return articleComments;

    }

    @Override
    public List<Draft> getDraftList(Integer authorId, int pageStart, int pageEnd) {

        List<Draft> drafts = draftMapper.selectDraftList(authorId, pageStart, pageEnd);

        return drafts;
    }

    @Override
    public Draft getDraftDetail(Integer draftId) {

        Draft draft = draftMapper.selectDraftDetailById(draftId);

        return draft;
    }

    @Override
    public Integer addOneDraft(Draft draft) {

        draftMapper.insertOneDraft(draft);
        Integer draftId = draft.getId();
        return draftId;
    }

    @Override
    public Boolean putOneDraft(Draft draft) {
        Boolean hasUpdate = draftMapper.updateOneDraft(draft);

        return hasUpdate;
    }

    @Override
    public Boolean deleteOneDraft(Integer draftId) {
        Boolean hasDelete = draftMapper.deleteDraftById(draftId);
        return hasDelete;
    }

    @Override
    public Boolean putOneArticle(Article article) {

        Boolean hasUpdate = articleMapper.updateArticle(article);

        return hasUpdate;
    }

    @Override
    public Boolean deleteOneArticle(Integer articleId) {
        Boolean hasDelete = articleMapper.deleteArticleById(articleId);
        return hasDelete;
    }


}
