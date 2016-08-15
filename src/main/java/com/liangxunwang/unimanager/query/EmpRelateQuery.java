package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class EmpRelateQuery {
    private String emp_relate_id;
    private String mm_emp_id1;
    private String mm_emp_id2;
    private String state;

    public String getMm_emp_id2() {
        return mm_emp_id2;
    }

    public void setMm_emp_id2(String mm_emp_id2) {
        this.mm_emp_id2 = mm_emp_id2;
    }

    public String getEmp_relate_id() {
        return emp_relate_id;
    }

    public void setEmp_relate_id(String emp_relate_id) {
        this.emp_relate_id = emp_relate_id;
    }

    public String getMm_emp_id1() {
        return mm_emp_id1;
    }

    public void setMm_emp_id1(String mm_emp_id1) {
        this.mm_emp_id1 = mm_emp_id1;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
