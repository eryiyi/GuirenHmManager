package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 */
@Service("appEmpEditService")
public class AppEmpEditService implements  UpdateService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object update(Object object) throws ServiceException {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            empDao.editEmpById(emp);
        }
        return null;
    }
}
