package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class EmpQuery  extends BaseAreaQuery{
    private int index;
    private int size;
    private String keyword;
    private String mm_hangye_id;

    private String lat;
    private String lng;
    private String ischeck;
    private String mm_emp_regtime;
    private String mm_emp_type;
    private String mm_emp_company_type;

    public String getMm_emp_company_type() {
        return mm_emp_company_type;
    }

    public void setMm_emp_company_type(String mm_emp_company_type) {
        this.mm_emp_company_type = mm_emp_company_type;
    }

    public String getMm_emp_type() {
        return mm_emp_type;
    }

    public void setMm_emp_type(String mm_emp_type) {
        this.mm_emp_type = mm_emp_type;
    }

    public String getMm_emp_regtime() {
        return mm_emp_regtime;
    }

    public void setMm_emp_regtime(String mm_emp_regtime) {
        this.mm_emp_regtime = mm_emp_regtime;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMm_hangye_id() {
        return mm_hangye_id;
    }

    public void setMm_hangye_id(String mm_hangye_id) {
        this.mm_hangye_id = mm_hangye_id;
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
}
