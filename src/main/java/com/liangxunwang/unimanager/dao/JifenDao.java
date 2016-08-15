package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.EmpCount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("jifenDao")
public interface JifenDao {

    /**
     * 查询ad
     */
    List<EmpCount> lists(Map<String, Object> map);

    //保存
    void save(EmpCount adObj);

    /**
     * 根据ID查找
     * @param mm_emp_id
     * @return
     */
    public EmpCount findById(String mm_emp_id);

    /**
     * 更新
     * @param empCount
     */
    public void update(EmpCount empCount);
}
