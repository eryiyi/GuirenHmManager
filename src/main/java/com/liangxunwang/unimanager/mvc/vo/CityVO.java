package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.Emp;

/**
 * Created by Administrator on 2016/2/14.
 */
public class CityVO extends CityObj{
    private String provinceName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
