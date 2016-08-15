package com.liangxunwang.unimanager.model;

/**
 * Created by Administrator on 2016/2/16.
 */
public class CityObj {
    private String id;
    private String cityID;
    private String city;
    private String father;
    private String citynum;
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

    public String getCitynum() {
        return citynum;
    }

    public void setCitynum(String citynum) {
        this.citynum = citynum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
