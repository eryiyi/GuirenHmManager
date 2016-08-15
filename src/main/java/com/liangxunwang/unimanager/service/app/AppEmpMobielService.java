package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("appEmpMobielService")
public class AppEmpMobielService implements  UpdateService ,ExecuteService {
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

   //修改会员信息手机号
    @Override
    public Object update(Object object) throws ServiceException {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            empDao.updateMobile(emp);
        }
        return null;
    }
    @Override
    public Object execute(Object object) throws ServiceException {
            Object[] params = (Object[]) object;
            String hxUsername = (String) params[0];
            Map<String, Object> map = new HashMap<String, Object>();
            List<EmpVO> list = new ArrayList<EmpVO>();
            if(hxUsername!=null){
                String[] strs = hxUsername.split(",");
                List<String> phones = new ArrayList<String>();
                for (int i = 0; i < strs.length; i++) {
                    phones.add(strs[i]);
                }
                map.put("hxUsername", strs);
                list = empDao.listMemberInfoByUsername(map);
            }
            if(list != null && list.size()>0){
                for (EmpVO member : list) {
                    if (member.getMm_emp_cover().startsWith("upload")) {
                        member.setMm_emp_cover(Constants.URL + member.getMm_emp_cover());
                    }else {
                        member.setMm_emp_cover(Constants.QINIU_URL + member.getMm_emp_cover());
                    }
                }
            }
            return list;
    }



}
