package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("appEmpPwrService")
public class AppEmpPwrService implements  UpdateService {
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

   //修改会员信息
    @Override
    public Object update(Object object) throws ServiceException {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            //修改密码
            EmpVO empVO = empDao.findByMobile(emp.getMm_emp_mobile());
            if(empVO != null){
                //说明手机号正确
                emp.setMm_emp_password(new MD5Util().getMD5ofStr(emp.getMm_emp_password()));//密码加密
                empDao.updatePwr(emp);
            }else {
                throw new ServiceException("has_none");
            }
        }
        return null;
    }



}
