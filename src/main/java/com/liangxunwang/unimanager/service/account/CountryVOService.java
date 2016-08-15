package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CountryDao;
import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.mvc.vo.CountryVO;
import com.liangxunwang.unimanager.query.CountryQuery;
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
@Service("countryVOService")
public class CountryVOService implements ListService,ExecuteService,UpdateService {
    @Autowired
    @Qualifier("countryDao")
    private CountryDao countryDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CountryQuery query = (CountryQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getFather())){
            map.put("father", query.getFather());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        List<CountryVO> lists = countryDao.listsVo(map);
        return lists;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        String id = (String) object;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        CountryObj provinceObj = countryDao.findById(map);
        return provinceObj;
    }

    @Override
    public Object update(Object object) {
        CountryObj level = (CountryObj) object;
        countryDao.update(level);
        return null;
    }


}
