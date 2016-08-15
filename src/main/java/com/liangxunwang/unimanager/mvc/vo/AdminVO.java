package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CityObj;

/**
 * Created by Administrator on 2016/2/14.
 */
public class AdminVO extends Admin{
    private String provinceName;
    private String cityName;
    private String countryName;

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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
