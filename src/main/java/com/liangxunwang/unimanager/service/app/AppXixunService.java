package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.XixunDao;
import com.liangxunwang.unimanager.model.XixunObj;
import com.liangxunwang.unimanager.mvc.vo.XixunObjVO;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
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
@Service("appXixunService")
public class AppXixunService implements ListService ,SaveService{
    @Autowired
    @Qualifier("xixunDao")
    private XixunDao xixunDao;

    @Override
    public Object list(Object object) throws ServiceException {
        RecordQuery query = (RecordQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();
        map.put("index", index);
        map.put("size", size);
        List<XixunObjVO> list = xixunDao.listRecordVo(map);
        long count = xixunDao.count(map);
        for (XixunObjVO record : list){
            if (!StringUtil.isNullOrEmpty(record.getMm_emp_cover())){
                if (record.getMm_emp_cover().startsWith("upload")){
                    record.setMm_emp_cover(Constants.URL+record.getMm_emp_cover());
                }else {
                    record.setMm_emp_cover(Constants.QINIU_URL + record.getMm_emp_cover());
                }
            }
            record.setDateline(RelativeDateFormat.format(Long.parseLong(record.getDateline())));
        }
        return new Object[]{list, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        XixunObj record = (XixunObj) object;
        record.setGuiren_xixun_id(UUIDFactory.random());
        record.setDateline(System.currentTimeMillis() + "");
        record.setGuiren_xixun_title("欢迎"+ record.getMm_emp_nickname()+"加入贵人");
        xixunDao.save(record);
        return null;
    }

}
