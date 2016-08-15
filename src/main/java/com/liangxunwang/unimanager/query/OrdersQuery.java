package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2016/5/27.
 */
public class OrdersQuery {
    private String emp_id;
    private String mm_emp_nickname;
    private String mm_emp_mobile;
    private String pay_status;
    private String status;
    private int index;
    private int size;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getMm_emp_nickname() {
        return mm_emp_nickname;
    }

    public void setMm_emp_nickname(String mm_emp_nickname) {
        this.mm_emp_nickname = mm_emp_nickname;
    }

    public String getMm_emp_mobile() {
        return mm_emp_mobile;
    }

    public void setMm_emp_mobile(String mm_emp_mobile) {
        this.mm_emp_mobile = mm_emp_mobile;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
