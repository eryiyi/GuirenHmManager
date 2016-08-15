package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.mvc.vo.CountryVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("countryDao")
public interface CountryDao {

    /**
     * 查询区县根据城市ID
     */
    List<CountryObj> lists(Map<String, Object> map);
    //查询县区列表  及其所属城市 省份
    List<CountryVO> listsVo(Map<String, Object> map);


    //保存
    void save(CountryObj countryObj);

    //
    CountryObj findById(Map<String,Object> map);


    /**
     * 更新
     * @param countryObj
     */
    public void update(CountryObj countryObj);
}
