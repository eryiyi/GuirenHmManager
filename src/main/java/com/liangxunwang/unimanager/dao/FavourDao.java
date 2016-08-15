package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Favour;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.FavourVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("favourDao")
public interface FavourDao {

    /**
     * 查询收藏
     */
    List<FavourVO> lists(Map<String, Object> map);
//    long count(Map<String, Object> map);


    //保存
    void save(Favour favour);


    //删除
    void delete(String mm_record_favour_id);

    /**
     * 根据ID查找
     * @param mm_record_favour_id
     * @return
     */
    public Favour findById(String mm_record_favour_id);
    /**
     * 根据ID查找
     * @return
     */
    public Favour findOne(Map<String, Object> map);

    //查询收藏数量
    long count(Map<String,Object> map);


}
