package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpLoginNumDao;
import com.liangxunwang.unimanager.dao.LogoDao;
import com.liangxunwang.unimanager.model.EmpLoginNum;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.EmpLoginNumVO;
import com.liangxunwang.unimanager.mvc.vo.LogoVO;
import com.liangxunwang.unimanager.query.EmpLoginNumQuery;
import com.liangxunwang.unimanager.query.LogoQuery;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("empLoginNumService")
public class EmpLoginNumService implements ListService,SaveService {
    @Autowired
    @Qualifier("empLoginNumDao")
    private EmpLoginNumDao empLoginNumDao;

    @Override
    public Object list(Object object) throws ServiceException {
        EmpLoginNumQuery query = (EmpLoginNumQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getKeyword())){
            map.put("keyword", query.getKeyword());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            map.put("mm_emp_id", query.getMm_emp_id());
        }

        List<EmpLoginNumVO> lists = empLoginNumDao.listRecordVo(map);
        long count = empLoginNumDao.count(map);

        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        EmpLoginNum empLoginNum = (EmpLoginNum) object;
        empLoginNum.setMm_emp_login_num_id(UUIDFactory.random());
        empLoginNum.setMm_emp_login_time(DateUtil.getDateAndTime());
        empLoginNumDao.save(empLoginNum);
        return null;
    }

}
