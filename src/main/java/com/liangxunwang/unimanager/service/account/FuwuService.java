package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.FuwuDao;
import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.query.FuwuQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.DateUtil;
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
@Service("fuwuService")
public class FuwuService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("fuwuDao")
    private FuwuDao fuwuDao;

    @Autowired
    @Qualifier("accessTokenDao")
    private AccessTokenDao accessTokenDao;

    @Override
    public Object list(Object object) throws ServiceException {
        FuwuQuery query = (FuwuQuery) object;

        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(query.getLat())){
            map.put("lat", query.getLat());
        }
        if(!StringUtil.isNullOrEmpty(query.getLng())){
            map.put("lng", query.getLng());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_fuwu_type())){
            map.put("mm_fuwu_type", query.getMm_fuwu_type());
        }
        List<FuwuObj> lists = fuwuDao.lists(map);

        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        FuwuObj level = (FuwuObj) object;
        level.setMm_fuwu_id(UUIDFactory.random());
        level.setDateline(DateUtil.getDateAndTime());//时间戳
        fuwuDao.save(level);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_fuwu_id = (String) object;
        fuwuDao.delete(mm_fuwu_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return fuwuDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        FuwuObj level = (FuwuObj) object;
        fuwuDao.update(level);
        return null;
    }
}
