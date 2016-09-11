package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.VideoTypeObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("dianyingTypeDao")
public interface DianyingTypeDao {
    /**
     * 查询
     */
    List<VideoTypeObj> lists(Map<String, Object> map);

    //保存
    void save(VideoTypeObj videoTypeObj);

    /**
     * 根据ID查找
     * @param video_type_id
     * @return
     */
    public VideoTypeObj findById(String video_type_id);

    /**
     * 更新
     * @param videoTypeObj
     */
    public void update(VideoTypeObj videoTypeObj);
}
