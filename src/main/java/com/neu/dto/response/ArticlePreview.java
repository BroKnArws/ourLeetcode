package com.neu.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.vo.Author;

import java.util.Date;
import java.util.List;

public class ArticlePreview {
    private Integer id;
    private String title;
    private Integer browse;//点击数
    private String summary;//概括
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date initializeTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;
    private String thumbnail;//缩略图
    private String topicName;//话题名,比如算法和数据结构
    private List<String> tags;//存放标签的列表
    private Author author;//作者
    private Integer likes;
    private Integer thinks;
    private Integer surprises;
    private Integer articleComments;//评论数
//    private Boolean isMyFavorite;//是否被收藏



    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(Integer articleComments) {
        this.articleComments = articleComments;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Date getInitializeTime() {
        return initializeTime;
    }

    public void setInitializeTime(Date initializeTime) {
        this.initializeTime = initializeTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getThinks() {
        return thinks;
    }

    public void setThinks(Integer thinks) {
        this.thinks = thinks;
    }

    public Integer getSurprises() {
        return surprises;
    }

    public void setSurprises(Integer surprises) {
        this.surprises = surprises;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }



    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }






}
