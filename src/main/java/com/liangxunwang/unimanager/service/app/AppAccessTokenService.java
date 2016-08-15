package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.Level;
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
@Service("appAccessTokenService")
public class AppAccessTokenService implements SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("accessTokenDao")
    private AccessTokenDao accessTokenDao;

    @Override
    public Object save(Object object) throws ServiceException {
        AccessToken level = (AccessToken) object;
        level.setMm_access_token_id(UUIDFactory.random());
        accessTokenDao.save(level);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_emp_id = (String) object;
        accessTokenDao.delete(mm_emp_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return accessTokenDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        AccessToken accessToken = (AccessToken) object;
        accessTokenDao.update(accessToken);
        return null;
    }
}
