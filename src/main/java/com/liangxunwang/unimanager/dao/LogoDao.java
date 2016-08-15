package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.LogoVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("logoDao")
public interface LogoDao {

    /**
     * 查询日志信息
     */
    List<LogoVO> listRecordVo(Map<String, Object> map);
    long count(Map<String, Object> map);


    //保存
    void save(LogoObj level);
    //删除
    void delete(String mm_logo_id);

}
