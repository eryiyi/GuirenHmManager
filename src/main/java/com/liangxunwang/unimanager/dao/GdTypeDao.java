package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.GdTypeObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("gdTypeDao")
public interface GdTypeDao {

    /**
     * 查询gdTypeObj
     */
    List<GdTypeObj> lists(Map<String, Object> map);

    //保存
    void save(GdTypeObj gdTypeObj);

    //删除
    void delete(String gd_type_id);

    /**
     * 根据ID查找
     * @param gd_type_id
     * @return
     */
    public GdTypeObj findById(String gd_type_id);

    /**
     * 更新
     * @param gdTypeObj
     */
    public void update(GdTypeObj gdTypeObj);
}
