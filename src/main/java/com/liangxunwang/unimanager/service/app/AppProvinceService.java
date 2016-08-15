package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.FuwuDao;
import com.liangxunwang.unimanager.dao.ProvinceDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.query.FuwuQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.DateUtil;
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
@Service("appProvinceService")
public class AppProvinceService implements ListService{
    @Autowired
    @Qualifier("provinceDao")
    private ProvinceDao provinceDao;

    @Override
    public Object list(Object object) throws ServiceException {
        ProvinceQuery query = (ProvinceQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        List<ProvinceObj> lists = provinceDao.listsApp(map);

        return lists;
    }
}
