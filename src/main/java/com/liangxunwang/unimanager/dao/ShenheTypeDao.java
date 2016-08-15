package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.ShenheTypeObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository("shenheTypeDao")
public interface ShenheTypeDao {

    /**
     * 查询
     */
    List<ShenheTypeObj> lists(Map<String, Object> map);

    //保存
    void save(ShenheTypeObj shenheTypeObj);


    /**
     * 更新
     * @param shenheTypeObj
     */
    public void update(ShenheTypeObj shenheTypeObj);

}
