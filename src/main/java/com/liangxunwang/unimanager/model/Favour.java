package com.liangxunwang.unimanager.model;

/**
 * Created by zhanghailong on 2016/3/3.
 */
public class Favour {
    private String mm_record_favour_id;
    private String mm_msg_id;
    private String mm_emp_id;
    private String dateline;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMm_record_favour_id() {
        return mm_record_favour_id;
    }

    public void setMm_record_favour_id(String mm_record_favour_id) {
        this.mm_record_favour_id = mm_record_favour_id;
    }

    public String getMm_msg_id() {
        return mm_msg_id;
    }

    public void setMm_msg_id(String mm_msg_id) {
        this.mm_msg_id = mm_msg_id;
    }

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
