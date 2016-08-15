package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.KefuTel;

/**
 * Created by Administrator on 2016/2/14.
 */
public class KefuVO extends KefuTel{

    private String provinceName;
    private String cityName;
    private String areaName;


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
