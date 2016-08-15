package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.FeiyongObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("aboutUsDao")
public interface AboutUsDao {

    /**
     * 查询
     */
    List<AboutUs> lists(Map<String, Object> map);

    //保存
    void save(AboutUs aboutUs);



    /**
     * 更新
     * @param aboutUs
     */
    public void update(AboutUs aboutUs);

}
