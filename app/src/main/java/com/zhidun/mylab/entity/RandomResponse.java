package com.zhidun.mylab.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class RandomResponse {

    /**
     * _id : 56dd4f646776592b6246e97c
     * createdAt : 2016-03-07T17:52:36.185Z
     * desc : 亲身实践后记录的博客，Git分支管理。
     * publishedAt : 2016-03-08T12:55:59.161Z
     * source : web
     * type : Android
     * url : http://sfsheng0322.github.io/2016/02/29/git-branch.html
     * used : true
     * who : 孙福生
     * images : ["http://img.gank.io/7b5a0968-e736-4f17-9273-9c7e3e55c76c"]
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
