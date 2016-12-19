package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.HelpType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("helpTypeDao")
public interface HelpTypeDao {

    /**
     * 查询ad
     */
    List<HelpType> lists(Map<String, Object> map);

    //保存
    void save(HelpType helpType);

    //删除
    void delete(String help_type_id);

    /**
     * 根据ID查找
     */
    public HelpType findById(String help_type_id);

    /**
     * 更新
     */
    public void update(HelpType helpType);
}
