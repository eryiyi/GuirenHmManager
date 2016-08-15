package com.liangxunwang.unimanager.model;

/**
 * Created by Administrator on 2016/2/16.
 */
public class CountryObj {
    private String id;
    private String areaID;
    private String area;
    private String father;
    private String msgNum;
    private String is_use;
    private String topnum;

    public String getTopnum() {
        return topnum;
    }

    public void setTopnum(String topnum) {
        this.topnum = topnum;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    public String getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(String msgNum) {
        this.msgNum = msgNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public CountryObj() {
    }


    public CountryObj(String areaID, String area) {
        this.areaID = areaID;
        this.area = area;
    }
}
