package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AboutUsDao;
import com.liangxunwang.unimanager.dao.NearbyDistanceObjDao;
import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.NearbyDistanceObj;
import com.liangxunwang.unimanager.service.*;
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
@Service("nearbyDistanceObjService")
public class NearbyDistanceObjService implements ListService,SaveService ,UpdateService{
    @Autowired
    @Qualifier("nearbyDistanceObjDao")
    private NearbyDistanceObjDao nearbyDistanceObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<NearbyDistanceObj> lists = nearbyDistanceObjDao.lists(map);
        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        NearbyDistanceObj aboutUs = (NearbyDistanceObj) object;
        aboutUs.setMm_nearby_distance_id(UUIDFactory.random());
        nearbyDistanceObjDao.save(aboutUs);
        return null;
    }


    @Override
    public Object update(Object object) {
        NearbyDistanceObj feiyongObj = (NearbyDistanceObj) object;
        nearbyDistanceObjDao.update(feiyongObj);
        return null;
    }
}
