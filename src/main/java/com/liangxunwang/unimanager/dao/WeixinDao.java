package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.model.WeixinObj;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository("weixinDao")
public interface WeixinDao {

    /**
     * 查询微信
     */
    List<WeixinObj> lists(Map<String, Object> map);
//    long count(Map<String, Object> map);


    //保存
    void save(WeixinObj fuwuObj);


    /**
     * 更新
     * @param fuwuObj
     */
    public void update(WeixinObj fuwuObj);


    /**
     * 根据ID查找
     * @param mm_tel_id
     * @return
     */
    public WeixinObj findById(String mm_tel_id);

    //删除
    void delete(String mm_weixin_id);
}
