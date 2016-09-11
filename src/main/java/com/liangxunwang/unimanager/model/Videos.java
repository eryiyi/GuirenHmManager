package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/3.
 */
public class Videos {
    private String id;
    private String title;
    private String content;
    private String picUrl;
    private String videoUrl;
    private String isdel;
    private String dateline;
    private String video_type_id;

    public String getVideo_type_id() {
        return video_type_id;
    }

    public void setVideo_type_id(String video_type_id) {
        this.video_type_id = video_type_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
