package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.dao.SuggestDao;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.mvc.vo.SuggestVO;
import com.liangxunwang.unimanager.query.LevelQuery;
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
@Service("suggestService")
public class SuggestService implements ListService,SaveService ,DeleteService,ExecuteService{
    @Autowired
    @Qualifier("suggestDao")
    private SuggestDao suggestDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SuggestVO> lists = suggestDao.lists(map);

        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        SuggestObj level = (SuggestObj) object;
        level.setMm_suggest_id(UUIDFactory.random());
        level.setDateline(System.currentTimeMillis() + "");
        suggestDao.save(level);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_suggest_id = (String) object;
        suggestDao.delete(mm_suggest_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return suggestDao.findById((String) object);
    }

}
