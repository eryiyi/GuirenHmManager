package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Emp;

/**
 * Created by Administrator on 2016/2/14.
 */
public class EmpVO extends Emp{

    private String mm_hangye_name;
    private String areaName;

    public String getMm_hangye_name() {
        return mm_hangye_name;
    }

    public void setMm_hangye_name(String mm_hangye_name) {
        this.mm_hangye_name = mm_hangye_name;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
