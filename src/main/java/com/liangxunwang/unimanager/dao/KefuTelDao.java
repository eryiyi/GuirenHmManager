package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository("kefuTelDao")
public interface KefuTelDao {

    /**
     * 查询客服
     */
    List<KefuVO> lists(Map<String, Object> map);
    List<KefuVO> listsAll(Map<String, Object> map);
//    long count(Map<String, Object> map);


    //保存
    void save(KefuTel fuwuObj);


    /**
     * 更新
     * @param fuwuObj
     */
    public void update(KefuTel fuwuObj);


    /**
     * 根据ID查找
     * @param mm_tel_id
     * @return
     */
    public KefuTel findById(String mm_tel_id);

}
