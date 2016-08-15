package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.HangYeTypeDao;
import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.model.HangYeType;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.HangYeTypeVO;
import com.liangxunwang.unimanager.query.HangYeTypeQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
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
@Service("hangYeTypeService")
public class HangYeTypeService implements ListService,SaveService ,DeleteService,ExecuteService, UpdateService{
    @Autowired
    @Qualifier("hangYeTypeDao")
    private HangYeTypeDao hangYeTypeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        HangYeTypeQuery query = (HangYeTypeQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_hangye_fid())){
            map.put("mm_hangye_fid", query.getMm_hangye_fid());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeywords())){
            map.put("keywords", query.getKeywords());
        }

        List<HangYeTypeVO> lists = hangYeTypeDao.lists(map);

        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        HangYeType hangYeType = (HangYeType) object;
        hangYeType.setMm_hangye_id(UUIDFactory.random());
        hangYeTypeDao.save(hangYeType);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_hangye_id = (String) object;
        hangYeTypeDao.delete(mm_hangye_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return hangYeTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        HangYeType hangYeType = (HangYeType) object;
        hangYeTypeDao.update(hangYeType);
        return null;
    }
}
