package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.FeiyongDao;
import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.FeiyongVO;
import com.liangxunwang.unimanager.query.FeiyongQuery;
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
@Service("feiyongService")
public class FeiyongService implements ListService,SaveService,DeleteService ,ExecuteService,UpdateService{
    @Autowired
    @Qualifier("feiyongDao")
    private FeiyongDao feiyongDao;

    @Override
    public Object list(Object object) throws ServiceException {
        FeiyongQuery query = (FeiyongQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        List<FeiyongVO> lists = feiyongDao.lists(map);
        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        FeiyongObj level = (FeiyongObj) object;
        level.setMm_feiyong_id(UUIDFactory.random());
        feiyongDao.save(level);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_feiyong_id = (String) object;
        feiyongDao.delete(mm_feiyong_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return feiyongDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        FeiyongObj feiyongObj = (FeiyongObj) object;
        feiyongDao.update(feiyongObj);
        return null;
    }
}
