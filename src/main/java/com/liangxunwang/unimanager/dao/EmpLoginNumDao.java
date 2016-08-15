package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.EmpLoginNum;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.EmpLoginNumVO;
import com.liangxunwang.unimanager.mvc.vo.LogoVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("empLoginNumDao")
public interface EmpLoginNumDao {

    /**
     * 查询信息
     */
    List<EmpLoginNumVO> listRecordVo(Map<String, Object> map);
    long count(Map<String, Object> map);

    //保存
    void save(EmpLoginNum empLoginNum);

}
