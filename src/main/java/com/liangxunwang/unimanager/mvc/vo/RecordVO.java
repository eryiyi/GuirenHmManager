package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Record;

/**
 * Created by Administrator on 2016/2/14.
 */
public class RecordVO extends Record {
    private String mm_emp_mobile;
    private String mm_emp_nickname;
    private String mm_emp_cover;

    private String deviceType;
    private String mm_emp_sex;
    private String mm_emp_birthday;
    private String mm_hangye_id;
    private String mm_emp_company;


    private String mm_emp_motto;
    private String levelName;//会员等级
    private String zanNum;//赞数量
    private String plNum;//评论数量
    private String hangye;//所属行业

    public String getMm_emp_motto() {
        return mm_emp_motto;
    }

    public void setMm_emp_motto(String mm_emp_motto) {
        this.mm_emp_motto = mm_emp_motto;
    }

    public String getHangye() {
        return hangye;
    }

    public void setHangye(String hangye) {
        this.hangye = hangye;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    public String getMm_emp_company() {
        return mm_emp_company;
    }

    public void setMm_emp_company(String mm_emp_company) {
        this.mm_emp_company = mm_emp_company;
    }

    public String getMm_emp_mobile() {
        return mm_emp_mobile;
    }

    public void setMm_emp_mobile(String mm_emp_mobile) {
        this.mm_emp_mobile = mm_emp_mobile;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMm_emp_sex() {
        return mm_emp_sex;
    }

    public void setMm_emp_sex(String mm_emp_sex) {
        this.mm_emp_sex = mm_emp_sex;
    }

    public String getMm_emp_birthday() {
        return mm_emp_birthday;
    }

    public void setMm_emp_birthday(String mm_emp_birthday) {
        this.mm_emp_birthday = mm_emp_birthday;
    }


    public String getMm_hangye_id() {
        return mm_hangye_id;
    }

    public void setMm_hangye_id(String mm_hangye_id) {
        this.mm_hangye_id = mm_hangye_id;
    }
}
