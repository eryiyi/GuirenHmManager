package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class MemberQuery  extends BaseAreaQuery{
    private int index;
    private int size;
    private String name;
    private String emp_id;
    private String gd_type_id;

    public String getGd_type_id() {
        return gd_type_id;
    }

    public void setGd_type_id(String gd_type_id) {
        this.gd_type_id = gd_type_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
