package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.SuggestDao;
import com.liangxunwang.unimanager.dao.VipEndTimeDao;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.model.VipEndTime;
import com.liangxunwang.unimanager.mvc.vo.SuggestVO;
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
@Service("vipEndTimeService")
public class VipEndTimeService implements ListService ,UpdateService,ExecuteService{
    @Autowired
    @Qualifier("vipEndTimeDao")
    private VipEndTimeDao vipEndTimeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<VipEndTime> lists = vipEndTimeDao.lists(map);
        return lists;
    }


    @Override
    public Object update(Object object) throws ServiceException {
        VipEndTime vipEndTime = (VipEndTime) object;
        vipEndTimeDao.update(vipEndTime);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return vipEndTimeDao.findById((String) object);
    }

}
