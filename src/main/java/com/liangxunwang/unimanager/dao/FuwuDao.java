package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.Level;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("fuwuDao")
public interface FuwuDao {

    /**
     * 查询服务
     */
    List<FuwuObj> lists(Map<String, Object> map);
//    long count(Map<String, Object> map);


    //保存
    void save(FuwuObj fuwuObj);


    //删除
    void delete(String mm_fuwu_id);

    /**
     * 根据ID查找
     * @param mm_fuwu_id
     * @return
     */
    public FuwuObj findById(String mm_fuwu_id);

    /**
     * 更新
     * @param fuwuObj
     */
    public void update(FuwuObj fuwuObj);
}
