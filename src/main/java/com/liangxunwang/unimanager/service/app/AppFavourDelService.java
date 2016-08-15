package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.FavourDao;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("appFavourDelService")
public class AppFavourDelService implements ExecuteService{

    @Autowired
    @Qualifier("favourDao")
    private FavourDao favourDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        String mm_record_favour_id = (String) object;
        favourDao.delete(mm_record_favour_id);
        return null;
    }

}
