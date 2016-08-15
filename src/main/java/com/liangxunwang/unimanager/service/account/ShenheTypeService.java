package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.ShenheTypeDao;
import com.liangxunwang.unimanager.model.ShenheTypeObj;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shenheTypeService")
public class ShenheTypeService implements ListService,SaveService ,UpdateService{
    @Autowired
    @Qualifier("shenheTypeDao")
    private ShenheTypeDao aboutUsDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShenheTypeObj> lists = aboutUsDao.lists(map);
        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        ShenheTypeObj aboutUs = (ShenheTypeObj) object;
        aboutUs.setMm_shenhe_type_id(UUIDFactory.random());
        aboutUsDao.save(aboutUs);
        return null;
    }


    @Override
    public Object update(Object object) {
        ShenheTypeObj feiyongObj = (ShenheTypeObj) object;
        aboutUsDao.update(feiyongObj);
        return null;
    }
}
