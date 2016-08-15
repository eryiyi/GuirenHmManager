package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class HangYeTypeQuery{
    private String mm_hangye_id;
    private String mm_hangye_fid;
    private String mm_hangye_name;
    private String keywords;
    private String is_top;

    public String getIs_top() {
        return is_top;
    }

    public void setIs_top(String is_top) {
        this.is_top = is_top;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMm_hangye_id() {
        return mm_hangye_id;
    }

    public void setMm_hangye_id(String mm_hangye_id) {
        this.mm_hangye_id = mm_hangye_id;
    }

    public String getMm_hangye_fid() {
        return mm_hangye_fid;
    }

    public void setMm_hangye_fid(String mm_hangye_fid) {
        this.mm_hangye_fid = mm_hangye_fid;
    }

    public String getMm_hangye_name() {
        return mm_hangye_name;
    }

    public void setMm_hangye_name(String mm_hangye_name) {
        this.mm_hangye_name = mm_hangye_name;
    }
}
