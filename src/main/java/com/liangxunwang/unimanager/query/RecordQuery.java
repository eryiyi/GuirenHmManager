package com.liangxunwang.unimanager.query;

/**
 */
public class RecordQuery  extends BaseAreaQuery{
    private int index;
    private int size;
    private String mm_msg_type;
    private String mm_emp_id;
    private String keyword;

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

    public String getMm_msg_type() {
        return mm_msg_type;
    }

    public void setMm_msg_type(String mm_msg_type) {
        this.mm_msg_type = mm_msg_type;
    }

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
