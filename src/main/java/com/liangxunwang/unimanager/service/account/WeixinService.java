package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.KefuTelDao;
import com.liangxunwang.unimanager.dao.WeixinDao;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.model.WeixinObj;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.KefuQuery;
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
@Service("weixinService")
public class WeixinService implements ListService,SaveService , UpdateService, ExecuteService,DeleteService{

    @Autowired
    @Qualifier("weixinDao")
    private WeixinDao weixinDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<WeixinObj> lists = weixinDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        WeixinObj level = (WeixinObj) object;
        level.setMm_weixin_id(UUIDFactory.random());
        weixinDao.save(level);
        return null;
    }

    @Override
    public Object update(Object object) {
        WeixinObj level = (WeixinObj) object;
        weixinDao.update(level);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return weixinDao.findById((String) object);
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_weixin_id = (String) object;
        weixinDao.delete(mm_weixin_id);
        return null;
    }
}
