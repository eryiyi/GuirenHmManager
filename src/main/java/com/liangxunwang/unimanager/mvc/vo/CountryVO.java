package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;

/**
 * Created by Administrator on 2016/2/14.
 */
public class CountryVO extends CountryObj{
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
