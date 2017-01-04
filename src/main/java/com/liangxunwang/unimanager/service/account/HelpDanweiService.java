package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.HelpDanweiDao;
import com.liangxunwang.unimanager.dao.HelpTypeDao;
import com.liangxunwang.unimanager.model.HelpDanwei;
import com.liangxunwang.unimanager.model.HelpType;
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
@Service("helpDanweiService")
public class HelpDanweiService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("helpDanweiDao")
    private HelpDanweiDao helpDanweiDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<HelpDanwei> lists = helpDanweiDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        HelpDanwei helpType = (HelpDanwei) object;
        helpType.setHelp_danwei_id(UUIDFactory.random());
        if(StringUtil.isNullOrEmpty(helpType.getTop_number())){
            helpType.setTop_number("0");
        }
        helpDanweiDao.save(helpType);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String help_danwei_id = (String) object;
        helpDanweiDao.delete(help_danwei_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return helpDanweiDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        HelpDanwei helpType = (HelpDanwei) object;
        helpDanweiDao.update(helpType);
        return null;
    }

}
