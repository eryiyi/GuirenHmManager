package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.EmpRelateObj;
import com.liangxunwang.unimanager.mvc.vo.EmpRelateObjVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("empRelateDao")
public interface EmpRelateDao {
    /**
     * 查询
     */
    List<EmpRelateObjVO> lists(Map<String, Object> map);

    //保存
    void save(EmpRelateObj empRelateObj);

    //删除
    void delete(String emp_relate_id);

    /**
     * 更新
     */
    public void update(EmpRelateObj empRelateObj);

    //根据两个人呢id查询是否已经存在关系
    EmpRelateObj find(EmpRelateObj empRelateObj);

    //根据id查询
    EmpRelateObj findById(String emp_relate_id);

    //查询是否有朋友---主动拜见
    List<EmpRelateObjVO> listsRelateOne(Map<String, Object> map);
    //查询是否有朋友---被拜见
    List<EmpRelateObjVO> listsRelateTwo(Map<String, Object> map);

}
