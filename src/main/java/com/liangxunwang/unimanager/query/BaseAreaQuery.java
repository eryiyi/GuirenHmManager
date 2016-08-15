package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class BaseAreaQuery {
    private String mm_emp_provinceId;
    private String mm_emp_cityId;
    private String mm_emp_countryId;
    private String is_use;

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    public String getMm_emp_provinceId() {
        return mm_emp_provinceId;
    }

    public void setMm_emp_provinceId(String mm_emp_provinceId) {
        this.mm_emp_provinceId = mm_emp_provinceId;
    }

    public String getMm_emp_cityId() {
        return mm_emp_cityId;
    }

    public void setMm_emp_cityId(String mm_emp_cityId) {
        this.mm_emp_cityId = mm_emp_cityId;
    }

    public String getMm_emp_countryId() {
        return mm_emp_countryId;
    }

    public void setMm_emp_countryId(String mm_emp_countryId) {
        this.mm_emp_countryId = mm_emp_countryId;
    }
}
