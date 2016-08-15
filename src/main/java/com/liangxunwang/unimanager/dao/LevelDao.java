package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("levelDao")
public interface LevelDao {

    /**
     * 查询等级
     */
    List<Level> lists(Map<String, Object> map);
//    long count(Map<String, Object> map);


    //保存
    void save(Level level);


    //删除
    void delete(String mm_level_id);

    /**
     * 根据ID查找
     * @param mm_level_id
     * @return
     */
    public Level findById(String mm_level_id);

    /**
     * 更新
     * @param level
     */
    public void update(Level level);
}
