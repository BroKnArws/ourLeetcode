package com.neu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.common.Utils;
import com.neu.dto.request.CreateArticleRequest;
import com.neu.dto.request.EditArticleRequest;

import javax.rmi.CORBA.Util;
import java.util.Date;

public class Article {

    private Integer id;
    private Integer creatorId;
    private Integer topicId;
    private String title;
    private String thumbnail;
    private String message;
    private String summary;//概括
    private Integer articleComments;
    private Integer likes;
    private Integer thinks;
    private Integer surprises;
    private Date initializeTime;
    private Date editTime;
    private Integer follows;
    private Integer browse;

    public Article(EditArticleRequest request) {
        this.id = request.getId();
        this.topicId = request.getTopicId();
        this.title = request.getTitle();
        this.thumbnail = request.getThumbnail();
        this.message = request.getMessage();
        this.summary = request.getSummary();
        this.editTime = Utils.currentTime();
    }

    public Article(CreateArticleRequest request) {
        this.topicId = request.getTopicId();
        this.title = request.getTitle();
        this.thumbnail = request.getThumbnail();
        this.message = request.getMessage();
        this.summary = request.getSummary();
        this.initializeTime = Utils.currentTime();
    }

    public Article() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(Integer articleComments) {
        this.articleComments = articleComments;
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

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }
}
