package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.HangYeType;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.HangYeTypeVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("hangYeTypeDao")
public interface HangYeTypeDao {

    /**
     * 查询所有分类
     */
    List<HangYeTypeVO> lists(Map<String, Object> map);

    //查询顶级父类
    List<HangYeTypeVO> listsTop(Map<String, Object> map);


    //保存
    void save(HangYeType hangYeType);


    //删除
    void delete(String mm_hangye_id);

    /**
     * 根据ID查找
     * @param mm_hangye_id
     * @return
     */
    public HangYeType findById(String mm_hangye_id);

    /**
     * 更新
     * @param hangYeType
     */
    public void update(HangYeType hangYeType);
}
