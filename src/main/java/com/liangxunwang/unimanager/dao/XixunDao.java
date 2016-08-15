package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.XixunObj;
import com.liangxunwang.unimanager.mvc.vo.XixunObjVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("xixunDao")
public interface XixunDao {

    /**
     * 查询
     */
    List<XixunObjVO> listRecordVo(Map<String, Object> map);

    long count(Map<String, Object> map);
    /**
     * 保存一条信息
     */
    void save(XixunObj xixunObj);

}
