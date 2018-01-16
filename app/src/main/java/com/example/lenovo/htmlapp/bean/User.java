package com.example.lenovo.htmlapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2018/1/14.
 */
 @Entity
public class User {
    @Id(autoincrement = true)

    private Long id;

    private String title;
    private String url;
    private String time;
    @Generated(hash = 1749222669)
    public User(Long id, String title, String url, String time) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.time = time;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
