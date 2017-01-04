package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.HelpDanwei;
import com.liangxunwang.unimanager.model.HelpType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("helpDanweiDao")
public interface HelpDanweiDao {

    /**
     * 查询ad
     */
    List<HelpDanwei> lists(Map<String, Object> map);

    //保存
    void save(HelpDanwei helpType);

    //删除
    void delete(String help_danwei_id);

    /**
     * 根据ID查找
     */
    public HelpDanwei findById(String help_danwei_id);

    /**
     * 更新
     */
    public void update(HelpDanwei helpType);
}
