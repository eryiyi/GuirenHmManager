package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.EmpRelateObj;

/**
 * Created by zhl on 2016/7/13.
 */
public class EmpRelateObjVO extends EmpRelateObj {
//    private String provinceName;
//    private String cityName;
//    private String mm_hangye_name;
//    private String areaName;

    private String mm_emp_id;
    private String mm_emp_nickname;
    private String mm_emp_cover;
    private String hxusername;

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getMm_emp_nickname() {
        return mm_emp_nickname;
    }

    public void setMm_emp_nickname(String mm_emp_nickname) {
        this.mm_emp_nickname = mm_emp_nickname;
    }

    public String getMm_emp_cover() {
        return mm_emp_cover;
    }

    public void setMm_emp_cover(String mm_emp_cover) {
        this.mm_emp_cover = mm_emp_cover;
    }

    public String getHxusername() {
        return hxusername;
    }

    public void setHxusername(String hxusername) {
        this.hxusername = hxusername;
    }
}
