package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.GuanzhuAreaObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.GuanzhuAreaObjVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("guanzhuAreaDao")
public interface GuanzhuAreaDao {
    /**
     * 查询关注区域
     */
    List<GuanzhuAreaObjVO> lists(Map<String, Object> map);

    long count(Map<String, Object> map);

    //保存
    void save(GuanzhuAreaObj guanzhuAreaObj);

    //删除
    void delete(String mm_level_id);

    /**
     * 根据ID查找
     * @param mm_guanzhu_id
     * @return
     */
    public GuanzhuAreaObjVO findById(String mm_guanzhu_id);

    //根据用户ID查询
    public GuanzhuAreaObjVO findByEmpId(String mm_emp_id);

    /**
     * 更新
     * @param level
     */
    public void update(GuanzhuAreaObj level);
}
