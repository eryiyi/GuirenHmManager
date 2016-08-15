package com.liangxunwang.unimanager.model;

/**
 * Created by Administrator on 2016/2/14.
 */
public class LogoObj {
    private String mm_logo_id;
    private String mm_logo_content;
    private String mm_manager_id;
    private String dateline;

    public LogoObj() {
        super();
    }

    public String getMm_logo_id() {
        return mm_logo_id;
    }

    public void setMm_logo_id(String mm_logo_id) {
        this.mm_logo_id = mm_logo_id;
    }

    public String getMm_logo_content() {
        return mm_logo_content;
    }

    public void setMm_logo_content(String mm_logo_content) {
        this.mm_logo_content = mm_logo_content;
    }

    public String getMm_manager_id() {
        return mm_manager_id;
    }

    public void setMm_manager_id(String mm_manager_id) {
        this.mm_manager_id = mm_manager_id;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public LogoObj(String mm_logo_content, String mm_manager_id) {
        this.mm_logo_content = mm_logo_content;
        this.mm_manager_id = mm_manager_id;
    }
}
