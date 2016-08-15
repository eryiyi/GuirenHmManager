package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/5/19.
 */
public class YaoqingCard {
    private String guiren_card_id;
    private String guiren_card_num;
    private String mm_emp_id;
    private String is_use;
    private String y_mm_emp_id;
    private String createtime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getY_mm_emp_id() {
        return y_mm_emp_id;
    }

    public void setY_mm_emp_id(String y_mm_emp_id) {
        this.y_mm_emp_id = y_mm_emp_id;
    }

    public String getGuiren_card_id() {
        return guiren_card_id;
    }

    public void setGuiren_card_id(String guiren_card_id) {
        this.guiren_card_id = guiren_card_id;
    }

    public String getGuiren_card_num() {
        return guiren_card_num;
    }

    public void setGuiren_card_num(String guiren_card_num) {
        this.guiren_card_num = guiren_card_num;
    }

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }
}
