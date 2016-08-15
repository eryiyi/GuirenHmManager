package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.FavourDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.Favour;
import com.liangxunwang.unimanager.mvc.vo.FavourVO;
import com.liangxunwang.unimanager.query.FavourQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("appFavourCountService")
public class AppFavourCountService implements ExecuteService{

    @Autowired
    @Qualifier("favourDao")
    private FavourDao favourDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        String mm_emp_id = (String) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtil.isNullOrEmpty(mm_emp_id)) {
            map.put("mm_emp_id", mm_emp_id);
        }
        long count = favourDao.count(map);
        return String.valueOf(count);
    }

}
