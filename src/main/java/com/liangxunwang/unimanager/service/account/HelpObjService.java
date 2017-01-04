package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.HelpObjDao;
import com.liangxunwang.unimanager.model.HelpObj;
import com.liangxunwang.unimanager.query.HelpObjQuery;
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
 */
@Service("helpObjService")
public class HelpObjService implements ListService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("helpObjDao")
    private HelpObjDao helpObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        HelpObjQuery query = (HelpObjQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getHelp_danwei_id())){
            map.put("help_danwei_id", query.getHelp_danwei_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getHelp_type_id())){
            map.put("help_type_id", query.getHelp_type_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id", query.getMm_emp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeywords())){
            map.put("keywords", query.getKeywords());
        }

        List<HelpObj> lists = helpObjDao.lists(map);
        long count = helpObjDao.count(map);

        return new Object[]{lists, count};
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String help_id = (String) object;
        helpObjDao.delete(help_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return helpObjDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        HelpObj helpObj = (HelpObj) object;
        helpObjDao.update(helpObj);
        return null;
    }
}
