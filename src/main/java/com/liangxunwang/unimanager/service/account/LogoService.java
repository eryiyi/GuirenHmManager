package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LogoDao;
import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.LogoVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.LogoQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.DeleteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("logoService")
public class LogoService implements ListService,SaveService,DeleteService {
    @Autowired
    @Qualifier("logoDao")
    private LogoDao logoDao;

    @Override
    public Object list(Object object) throws ServiceException {
        LogoQuery query = (LogoQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getKeyword())){
            map.put("keyword", query.getKeyword());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_manager_id())){
            map.put("mm_manager_id", query.getMm_manager_id());
        }

        List<LogoVO> lists = logoDao.listRecordVo(map);
        long count = logoDao.count(map);

        return new Object[]{lists, count};
    }


    @Override
    public Object save(Object object) throws ServiceException {
        LogoObj level = (LogoObj) object;
        level.setMm_logo_id(UUIDFactory.random());
        level.setDateline(DateUtil.getDateAndTime());
        logoDao.save(level);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_logo_id = (String) object;
        logoDao.delete(mm_logo_id);
        return null;
    }

}
