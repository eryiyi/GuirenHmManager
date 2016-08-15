package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class YaoqingCardQuery {
    private String guiren_card_id;
    private String guiren_card_num;
    private String mm_emp_id;
    private String y_mm_emp_id;
    private String is_use;
    private String keyword;
    private String add_one;//数量
    private int index;
    private int size;

    public String getY_mm_emp_id() {
        return y_mm_emp_id;
    }

    public void setY_mm_emp_id(String y_mm_emp_id) {
        this.y_mm_emp_id = y_mm_emp_id;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAdd_one() {
        return add_one;
    }

    public void setAdd_one(String add_one) {
        this.add_one = add_one;
    }

    public String getGuiren_card_id() {
        return guiren_card_id;
    }

    public void setGuiren_card_id(String guiren_card_id) {
        this.guiren_card_id = guiren_card_id;
    }

    public String getGuiren_card_num() {
        return guiren_card_num;
    }

    public void setGuiren_card_num(String guiren_card_num) {
        this.guiren_card_num = guiren_card_num;
    }

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }
}
