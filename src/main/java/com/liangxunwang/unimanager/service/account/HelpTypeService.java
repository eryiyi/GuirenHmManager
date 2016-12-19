package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.HelpTypeDao;
import com.liangxunwang.unimanager.model.HelpType;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.HelpTypeQuery;
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
@Service("helpTypeService")
public class HelpTypeService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("helpTypeDao")
    private HelpTypeDao helpTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        HelpTypeQuery query = (HelpTypeQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getHelp_type_id())){
            map.put("help_type_id", query.getHelp_type_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getHelp_type_f_id())){
            map.put("help_type_f_id", query.getHelp_type_f_id());
        }
        List<HelpType> lists = helpTypeDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        HelpType helpType = (HelpType) object;
        helpType.setHelp_type_id(UUIDFactory.random());
        if(StringUtil.isNullOrEmpty(helpType.getTop_number())){
            helpType.setTop_number("0");
        }
        helpTypeDao.save(helpType);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String help_type_id = (String) object;
        helpTypeDao.delete(help_type_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return helpTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        HelpType helpType = (HelpType) object;
        helpTypeDao.update(helpType);
        return null;
    }
}
