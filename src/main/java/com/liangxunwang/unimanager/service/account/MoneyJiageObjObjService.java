package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.MoneyJiageObjDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.MoneyJiageObj;
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
@Service("moneyJiageObjObjService")
public class MoneyJiageObjObjService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("moneyJiageObjDao")
    private MoneyJiageObjDao moneyJiageObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<MoneyJiageObj> lists = moneyJiageObjDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        MoneyJiageObj moneyJiageObj = (MoneyJiageObj) object;
        moneyJiageObj.setMoney_jiage_id(UUIDFactory.random());
        moneyJiageObjDao.save(moneyJiageObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String money_jiage_id = (String) object;
        moneyJiageObjDao.delete(money_jiage_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return moneyJiageObjDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        MoneyJiageObj moneyJiageObj = (MoneyJiageObj) object;
        moneyJiageObjDao.update(moneyJiageObj);
        return null;
    }

}
