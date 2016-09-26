package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.GdTypeDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.GdTypeObj;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
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
@Service("gdTypeObjService")
public class GdTypeObjService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("gdTypeDao")
    private GdTypeDao gdTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<GdTypeObj> lists = gdTypeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        GdTypeObj adObj = (GdTypeObj) object;
        adObj.setGd_type_id(UUIDFactory.random());
        gdTypeDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String gd_type_id = (String) object;
        gdTypeDao.delete(gd_type_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return gdTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        GdTypeObj adObj = (GdTypeObj) object;
        gdTypeDao.update(adObj);
        return null;
    }
}
