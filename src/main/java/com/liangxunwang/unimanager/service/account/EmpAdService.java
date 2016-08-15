package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpAdDao;
import com.liangxunwang.unimanager.dao.LevelDao;
import com.liangxunwang.unimanager.model.EmpAdObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.EmpAdVO;
import com.liangxunwang.unimanager.query.EmpAdQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("empAdService")
public class EmpAdService implements ListService,SaveService ,DeleteService,ExecuteService{
    @Autowired
    @Qualifier("empAdDao")
    private EmpAdDao empAdDao;

    @Override
    public Object list(Object object) throws ServiceException {
        EmpAdQuery query = (EmpAdQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtil.isNullOrEmpty(query.getMm_emp_id())) {
            map.put("mm_emp_id", query.getMm_emp_id());
        }
        List<EmpAdVO> lists = empAdDao.lists(map);
        for(EmpAdVO empAdVO:lists){
            if (empAdVO.getMm_emp_ad_pic().startsWith("upload")) {
                empAdVO.setMm_emp_ad_pic(Constants.URL + empAdVO.getMm_emp_ad_pic());
            }else {
                empAdVO.setMm_emp_ad_pic(Constants.QINIU_URL + empAdVO.getMm_emp_ad_pic());
            }
        }
        return lists;
    }


    @Override
    public Object save(Object object) throws ServiceException {
        EmpAdObj empAdVO = (EmpAdObj) object;
        empAdVO.setMm_emp_ad_id(UUIDFactory.random());
        empAdDao.save(empAdVO);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_emp_ad_id = (String) object;
        empAdDao.delete(mm_emp_ad_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return empAdDao.findById((String) object);
    }

}
