package com.neu.vo;

import com.neu.entity.LeetcodeUser;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

public class Author {
    private Integer id;

    private String userName;

    private String userAvatar;//头像url

    public Author(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }


    public Author(LeetcodeUser user){
        this.id = user.getId();
        this.userName = user.getName();
        this.userAvatar = user.getAvatar();

    }

}
