package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.model.VipEndTime;
import com.liangxunwang.unimanager.mvc.vo.SuggestVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("vipEndTimeDao")
public interface VipEndTimeDao {
    /**
     * 查询Vip到期日期
     */
    List<VipEndTime> lists(Map<String, Object> map);
    /**
     * 根据ID查找
     */
    public VipEndTime findById(String mm_endtime_vip_id);

    //更新数据
    void update(VipEndTime vipEndTime);
}
