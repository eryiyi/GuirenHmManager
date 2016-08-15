package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class AdminQuery extends BaseAreaQuery{
    private int index;
    private int size;
    private String mm_manager_is_use;

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

    public String getMm_manager_is_use() {
        return mm_manager_is_use;
    }

    public void setMm_manager_is_use(String mm_manager_is_use) {
        this.mm_manager_is_use = mm_manager_is_use;
    }
}
