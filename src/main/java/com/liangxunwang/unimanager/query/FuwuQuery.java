package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class FuwuQuery  extends BaseAreaQuery{
    private String mm_fuwu_type;
    private String lat;
    private String lng;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMm_fuwu_type() {
        return mm_fuwu_type;
    }

    public void setMm_fuwu_type(String mm_fuwu_type) {
        this.mm_fuwu_type = mm_fuwu_type;
    }
}
