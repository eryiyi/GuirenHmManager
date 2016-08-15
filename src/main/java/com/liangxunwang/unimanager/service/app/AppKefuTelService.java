package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.KefuTelDao;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appKefuTelService")
public class AppKefuTelService implements ListService{

    @Autowired
    @Qualifier("kefuTelDao")
    private KefuTelDao fuwuDao;

    @Override
    public Object list(Object object) throws ServiceException {
        KefuQuery query = (KefuQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_countryId())){
            map.put("mm_emp_countryId", query.getMm_emp_countryId());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_tel_type())){
            map.put("mm_tel_type", query.getMm_tel_type());
        }
        List<KefuVO> lists = fuwuDao.listsAll(map);
        return lists;
    }

}
