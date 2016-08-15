package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.dao.ProvinceDao;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("provinceService")
public class ProvinceService implements ListService,SaveService,ExecuteService,UpdateService{
    @Autowired
    @Qualifier("provinceDao")
    private ProvinceDao provinceDao;

    @Override
    public Object list(Object object) throws ServiceException {
        ProvinceQuery query = (ProvinceQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        List<ProvinceObj> lists = provinceDao.lists(map);

        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
//        ProvinceObj provinceObj = (ProvinceObj) object;
//        provinceObj.setId(UUIDFactory.random());
//        provinceDao.save(level);
        return null;
    }


    @Override
    public Object execute(Object object) throws ServiceException {
        String id = (String) object;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        ProvinceObj provinceObj = provinceDao.findById(map);
        return provinceObj;
    }

    @Override
    public Object update(Object object) {
        ProvinceObj level = (ProvinceObj) object;
        provinceDao.update(level);
        return null;
    }
}
