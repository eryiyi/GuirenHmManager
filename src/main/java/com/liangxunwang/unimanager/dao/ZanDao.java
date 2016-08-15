package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Zan;
import com.liangxunwang.unimanager.mvc.vo.ZanVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository("zanDao")
public interface ZanDao {
    /**
     * 保存一条赞信息
     */
    public void save(Zan zan);

    List<Zan> findByParams(@Param(value = "recordId") String recordId, @Param(value = "empId") String empId);

    /**
     * 根据动态ID查找所有的赞信息
     * @param recordId
     * @return
     */
    public List<ZanVO> listZanByRecord(String recordId);

}
