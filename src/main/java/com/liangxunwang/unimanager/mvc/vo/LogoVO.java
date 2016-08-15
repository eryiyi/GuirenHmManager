package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.LogoObj;

/**
 * Created by Administrator on 2016/2/14.
 */
public class LogoVO extends LogoObj{
    private String mm_manager_nickname;

    public String getMm_manager_nickname() {
        return mm_manager_nickname;
    }

    public void setMm_manager_nickname(String mm_manager_nickname) {
        this.mm_manager_nickname = mm_manager_nickname;
    }
}
