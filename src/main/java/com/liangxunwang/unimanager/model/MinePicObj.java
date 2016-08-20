package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/8/20.
 */
public class MinePicObj {
    private String picStr;
    private String mm_emp_id;

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getPicStr() {
        return picStr;
    }

    public void setPicStr(String picStr) {
        this.picStr = picStr;
    }

    public MinePicObj(String picStr, String mm_emp_id) {
        this.picStr = picStr;
        this.mm_emp_id = mm_emp_id;
    }
}
