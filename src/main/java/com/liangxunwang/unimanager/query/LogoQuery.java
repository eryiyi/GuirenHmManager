package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class LogoQuery  extends BaseAreaQuery{
    private int index;
    private int size;
    private String keyword;
    private String mm_manager_id;

    public String getMm_manager_id() {
        return mm_manager_id;
    }

    public void setMm_manager_id(String mm_manager_id) {
        this.mm_manager_id = mm_manager_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
