package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class HelpObjQuery {
    private int index;
    private int size;
    private String help_danwei_id;
    private String help_type_id;
    private String is_use;
    private String is_del;
    private String mm_emp_id;
    private String keywords;
    private String help_type;
    private String cityID;

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getHelp_type() {
        return help_type;
    }

    public void setHelp_type(String help_type) {
        this.help_type = help_type;
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

    public String getHelp_danwei_id() {
        return help_danwei_id;
    }

    public void setHelp_danwei_id(String help_danwei_id) {
        this.help_danwei_id = help_danwei_id;
    }

    public String getHelp_type_id() {
        return help_type_id;
    }

    public void setHelp_type_id(String help_type_id) {
        this.help_type_id = help_type_id;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
