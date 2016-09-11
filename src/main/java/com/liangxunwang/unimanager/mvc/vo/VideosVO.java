package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Videos;

/**
 * Created by zhl on 2015/2/2.
 */
public class VideosVO extends Videos {
    private String zanNum;//赞数量
    private String plNum;//评论数量
    private String video_type_name;//

    public String getVideo_type_name() {
        return video_type_name;
    }

    public void setVideo_type_name(String video_type_name) {
        this.video_type_name = video_type_name;
    }

    public String getZanNum() {
        return zanNum;
    }

    public void setZanNum(String zanNum) {
        this.zanNum = zanNum;
    }

    public String getPlNum() {
        return plNum;
    }

    public void setPlNum(String plNum) {
        this.plNum = plNum;
    }
}
