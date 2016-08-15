package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.ManagerInfoDao;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by liuzh on 2015/8/30.
 */
@Service("appManagerInfoService")
public class AppManagerInfoService implements  FindService,SaveService, UpdateService {

    @Autowired
    @Qualifier("managerInfoDao")
    private ManagerInfoDao managerInfoDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        if (object instanceof String){
            String emp_id = (String) object;
            ManagerInfo managerInfo = managerInfoDao.findByEmpId(emp_id);
            if(managerInfo != null){
                if(!StringUtil.isNullOrEmpty(managerInfo.getCompany_pic())){
                    if (managerInfo.getCompany_pic().startsWith("upload")) {
                        managerInfo.setCompany_pic(Constants.URL + managerInfo.getCompany_pic());
                    }else {
                        managerInfo.setCompany_pic(Constants.QINIU_URL + managerInfo.getCompany_pic());
                    }
                }
            }
            return managerInfo;
        }
        return null;
    }

    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

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
