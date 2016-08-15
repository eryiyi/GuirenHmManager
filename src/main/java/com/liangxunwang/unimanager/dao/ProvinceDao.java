package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.ProvinceObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("provinceDao")
public interface ProvinceDao {

    /**
     * 查询省份
     */
    List<ProvinceObj> lists(Map<String, Object> map);

    //保存
    void save(ProvinceObj level);

    /**
     * 查询省份
     */
    List<ProvinceObj> listsApp(Map<String, Object> map);

    //
    ProvinceObj findById(Map<String,Object> map);


    /**
     * 更新
     * @param provinceObj
     */
    public void update(ProvinceObj provinceObj);
}
