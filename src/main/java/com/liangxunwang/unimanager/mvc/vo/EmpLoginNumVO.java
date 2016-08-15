package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.EmpLoginNum;

/**
 * Created by Administrator on 2016/2/14.
 */
public class EmpLoginNumVO extends EmpLoginNum{
    private String mm_emp_mobile;
    private String mm_emp_nickname;

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
}
