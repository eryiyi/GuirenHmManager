package com.liangxunwang.unimanager.model;

/**
 */
public class Admin {
    private String mm_manager_id;
    private String mm_manager_mobile;
    private String mm_manager_nickname;
    private String mm_manager_password;
    private String mm_manager_type;
    private String mm_manager_area_uuid;
    private String mm_manager_is_use;
    private String permissions;
    private String goodsCount;
    private String mm_emp_id;

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getMm_manager_type() {
        return mm_manager_type;
    }

    public void setMm_manager_type(String mm_manager_type) {
        this.mm_manager_type = mm_manager_type;
    }

    public String getMm_manager_area_uuid() {
        return mm_manager_area_uuid;
    }

    public void setMm_manager_area_uuid(String mm_manager_area_uuid) {
        this.mm_manager_area_uuid = mm_manager_area_uuid;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getMm_manager_id() {
        return mm_manager_id;
    }

    public void setMm_manager_id(String mm_manager_id) {
        this.mm_manager_id = mm_manager_id;
    }

    public String getMm_manager_mobile() {
        return mm_manager_mobile;
    }

    public void setMm_manager_mobile(String mm_manager_mobile) {
        this.mm_manager_mobile = mm_manager_mobile;
    }

    public String getMm_manager_nickname() {
        return mm_manager_nickname;
    }

    public void setMm_manager_nickname(String mm_manager_nickname) {
        this.mm_manager_nickname = mm_manager_nickname;
    }

    public String getMm_manager_password() {
        return mm_manager_password;
    }

    public void setMm_manager_password(String mm_manager_password) {
        this.mm_manager_password = mm_manager_password;
    }

    public String getMm_manager_is_use() {
        return mm_manager_is_use;
    }

    public void setMm_manager_is_use(String mm_manager_is_use) {
        this.mm_manager_is_use = mm_manager_is_use;
    }
}
