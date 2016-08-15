package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 批量删除用户
 */
@Service("empPDeleteEmpService")
public class EmpPDeleteEmpService implements ExecuteService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object execute(Object object) throws Exception {
        String ids = (String) object;
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            for(int i=0;i<arrs.length;i++){
                if(!StringUtil.isNullOrEmpty(arrs[i])){
                    empDao.deleteEmp(arrs[i]);
                }
            }
        }
        return null;
    }




}
