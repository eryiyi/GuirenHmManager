package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.NearbyDistanceObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("nearbyDistanceObjDao")
public interface NearbyDistanceObjDao {

    /**
     * 查询
     */
    List<NearbyDistanceObj> lists(Map<String, Object> map);

    //保存
    void save(NearbyDistanceObj nearbyDistanceObj);



    /**
     * 更新
     * @param nearbyDistanceObj
     */
    public void update(NearbyDistanceObj nearbyDistanceObj);

}
