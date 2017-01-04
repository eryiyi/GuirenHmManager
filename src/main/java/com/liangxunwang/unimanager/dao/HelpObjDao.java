package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.HelpObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("helpObjDao")
public interface HelpObjDao {

    /**
     * 查询ad
     */
    List<HelpObj> lists(Map<String, Object> map);

    //保存
    void save(HelpObj helpObj);

    //删除
    void delete(String help_id);

    /**
     * 根据ID查找
     * @param help_id
     * @return
     */
    public HelpObj findById(String help_id);

    /**
     * 更新
     * @param helpObj
     */
    public void update(HelpObj helpObj);
    public void updateUse(HelpObj helpObj);
    public void updateDel(HelpObj helpObj);

    long count(Map<String,Object> map);
}
