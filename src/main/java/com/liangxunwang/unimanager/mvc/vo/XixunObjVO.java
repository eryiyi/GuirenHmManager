package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.XixunObj;

/**
 * Created by zhl on 2016/6/13.
 */
public class XixunObjVO extends XixunObj {
    private String mm_emp_cover;
    private String mm_hangye_name;
    private String mm_emp_sex;
    private String mm_emp_mobile;
    private String lat;
    private String lng;


    public String getMm_emp_sex() {
        return mm_emp_sex;
    }

    public void setMm_emp_sex(String mm_emp_sex) {
        this.mm_emp_sex = mm_emp_sex;
    }

    public String getMm_emp_mobile() {
        return mm_emp_mobile;
    }

    public void setMm_emp_mobile(String mm_emp_mobile) {
        this.mm_emp_mobile = mm_emp_mobile;
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

    public String getMm_emp_cover() {
        return mm_emp_cover;
    }

    public void setMm_emp_cover(String mm_emp_cover) {
        this.mm_emp_cover = mm_emp_cover;
    }

    public String getMm_hangye_name() {
        return mm_hangye_name;
    }

    public void setMm_hangye_name(String mm_hangye_name) {
        this.mm_hangye_name = mm_hangye_name;
    }
}
