package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.YaoqingCard;
import com.liangxunwang.unimanager.mvc.vo.YaoqingCardVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("yaoqingCardDao")
public interface YaoqingCardDao {
    //保存
    void save(YaoqingCard yaoqingCard);

    /**
     * 根据ID查找  根据邀请码查找
     */
    public YaoqingCard findById(Map<String, Object> map);

    //
//    public YaoqingCard findByCard(String guiren_card_num);


    //根据邀请人查找
    public  List<YaoqingCardVO>  list(Map<String, Object> map);
    public  List<YaoqingCardVO>  findByEmp(Map<String, Object> map);

    long count(Map<String,Object> map);

    /**
     * 更新
     */
    public void update(YaoqingCard yaoqingCard);
}
