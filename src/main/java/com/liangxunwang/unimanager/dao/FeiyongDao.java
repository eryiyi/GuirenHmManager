package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.FeiyongVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("feiyongDao")
public interface FeiyongDao {

    /**
     * 查询费用列表
     */
    List<FeiyongVO> lists(Map<String, Object> map);
//    long count(Map<String, Object> map);


    //保存
    void save(FeiyongObj feiyongObj);

    //删除
    void delete(String mm_feiyong_id);

    /**
     * 根据ID查找
     * @param mm_feiyong_id
     * @return
     */
    public FeiyongObj findById(String mm_feiyong_id);

    /**
     * 更新
     * @param feiyongObj
     */
    public void update(FeiyongObj feiyongObj);

}
