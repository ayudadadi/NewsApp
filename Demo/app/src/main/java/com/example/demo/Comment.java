package com.example.demo;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

//评论表
public class Comment extends BmobObject {
    private String userid;
    private String newsid;
    private String newsjson;
    private BmobDate time;
    private String content;

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

    public BmobDate getTime() {
        return time;
    }

    public void setTime(BmobDate time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
