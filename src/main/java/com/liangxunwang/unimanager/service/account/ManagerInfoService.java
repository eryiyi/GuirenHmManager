package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.ManagerInfoDao;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by liuzh on 2015/8/30.
 */
@Service("managerInfoService")
public class ManagerInfoService implements SaveService, FindService, UpdateService {

    @Autowired
    @Qualifier("managerInfoDao")
    private ManagerInfoDao managerInfoDao;

    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        if (object instanceof String){
            String id = (String) object;
            return managerInfoDao.findById(id);
        }
        return null;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        if (object instanceof ManagerInfo){
            ManagerInfo info = (ManagerInfo) object;
            info.setId(UUIDFactory.random());
            managerInfoDao.save(info);
            empDao.changeBusiness(info.getEmp_id(), "1");
        }
        return null;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof ManagerInfo){
            ManagerInfo info = (ManagerInfo) object;
            managerInfoDao.update(info);
        }
        return null;
    }
}
