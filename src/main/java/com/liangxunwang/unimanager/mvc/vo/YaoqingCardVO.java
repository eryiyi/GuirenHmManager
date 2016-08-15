package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.YaoqingCard;

/**
 * Created by zhl on 2016/5/19.
 */
public class YaoqingCardVO extends YaoqingCard {
    private String mm_emp_nickname;
    private String mm_emp_mobile;
    private String mm_emp_nickname_y;
    private String mm_emp_mobile_y;

    public String getMm_emp_nickname_y() {
        return mm_emp_nickname_y;
    }

    public void setMm_emp_nickname_y(String mm_emp_nickname_y) {
        this.mm_emp_nickname_y = mm_emp_nickname_y;
    }

    public String getMm_emp_mobile_y() {
        return mm_emp_mobile_y;
    }

    public void setMm_emp_mobile_y(String mm_emp_mobile_y) {
        this.mm_emp_mobile_y = mm_emp_mobile_y;
    }

    public String getMm_emp_nickname() {
        return mm_emp_nickname;
    }

    public void setMm_emp_nickname(String mm_emp_nickname) {
        this.mm_emp_nickname = mm_emp_nickname;
    }

    public String getMm_emp_mobile() {
        return mm_emp_mobile;
    }

    public void setMm_emp_mobile(String mm_emp_mobile) {
        this.mm_emp_mobile = mm_emp_mobile;
    }
}
