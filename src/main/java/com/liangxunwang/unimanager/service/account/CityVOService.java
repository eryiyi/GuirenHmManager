package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CityDao;
import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.mvc.vo.CityVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("cityVOService")
public class CityVOService implements ListService,ExecuteService,UpdateService {
    @Autowired
    @Qualifier("cityDao")
    private CityDao cityDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CityQuery query = (CityQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        if(!StringUtil.isNullOrEmpty(query.getFather())){
            map.put("father", query.getFather());
        }
        List<CityVO> lists = cityDao.listsVo(map);
        return lists;
    }


    @Override
    public Object execute(Object object) throws ServiceException {
        String id = (String) object;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        CityObj provinceObj = cityDao.findById(map);
        return provinceObj;
    }

    @Override
    public Object update(Object object) {
        CityObj level = (CityObj) object;
        cityDao.update(level);
        return null;
    }


}
