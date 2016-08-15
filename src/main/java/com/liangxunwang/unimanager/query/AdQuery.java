package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class AdQuery extends BaseAreaQuery{
    private String mm_ad_id;
    private String mm_ad_type;
    private String mm_emp_provinceId;
    private String mm_emp_cityId;
    private String mm_emp_countryId;

    @Override
    public String getMm_emp_provinceId() {
        return mm_emp_provinceId;
    }

    @Override
    public void setMm_emp_provinceId(String mm_emp_provinceId) {
        this.mm_emp_provinceId = mm_emp_provinceId;
    }

    @Override
    public String getMm_emp_cityId() {
        return mm_emp_cityId;
    }

    @Override
    public void setMm_emp_cityId(String mm_emp_cityId) {
        this.mm_emp_cityId = mm_emp_cityId;
    }

    @Override
    public String getMm_emp_countryId() {
        return mm_emp_countryId;
    }

    @Override
    public void setMm_emp_countryId(String mm_emp_countryId) {
        this.mm_emp_countryId = mm_emp_countryId;
    }

    public String getMm_ad_type() {
        return mm_ad_type;
    }

    public void setMm_ad_type(String mm_ad_type) {
        this.mm_ad_type = mm_ad_type;
    }

    public String getMm_ad_id() {
        return mm_ad_id;
    }

    public void setMm_ad_id(String mm_ad_id) {
        this.mm_ad_id = mm_ad_id;
    }
}
