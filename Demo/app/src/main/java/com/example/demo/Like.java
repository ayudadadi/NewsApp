package com.example.demo;

import cn.bmob.v3.BmobObject;

public class Like extends BmobObject {
    private String userid;
    private String newsid;
    private String newsjson;
    private boolean isLike;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getNewsjson() {
        return newsjson;
    }

    public void setNewsjson(String newsjson) {
        this.newsjson = newsjson;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
