package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.Level;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("accessTokenDao")
public interface AccessTokenDao {
    //保存
    void save(AccessToken accessToken);
    //删除
    void delete(String mm_emp_id);
    /**
     * 根据ID查找
     * @param mm_emp_id
     * @return
     */
    public AccessToken findById(String mm_emp_id);
    //根据accesstoken查看是否存在
    public AccessToken findByToken(String access_token);
    /**
     * 更新
     * @param accessToken
     */
    public void update(AccessToken accessToken);
}
