package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.MoneyJiageObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("moneyJiageObjDao")
public interface MoneyJiageObjDao {
    /**
     * 查询
     */
    List<MoneyJiageObj> lists(Map<String, Object> map);

    //保存
    void save(MoneyJiageObj moneyJiageObj);

    //删除
    void delete(String money_jiage_id);

    /**
     * 根据ID查找
     * @param money_jiage_id
     * @return
     */
    public MoneyJiageObj findById(String money_jiage_id);

    /**
     * 更新
     * @param moneyJiageObj
     */
    public void update(MoneyJiageObj moneyJiageObj);
}
