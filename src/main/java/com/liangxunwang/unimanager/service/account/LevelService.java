package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
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
@Service("levelService")
public class LevelService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("levelDao")
    private LevelDao leveldDao;

    @Override
    public Object list(Object object) throws ServiceException {
        LevelQuery query = (LevelQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        List<Level> lists = leveldDao.lists(map);

        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        Level level = (Level) object;
        level.setMm_level_id(UUIDFactory.random());
        leveldDao.save(level);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_level_id = (String) object;
        leveldDao.delete(mm_level_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return leveldDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        Level level = (Level) object;
        leveldDao.update(level);
        return null;
    }
}
