package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.mvc.vo.EmpDianpu;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("appMemberService")
public class AppMemberService implements ListService,UpdateService {
    @Autowired
    @Qualifier("empDao")
    private EmpDao memberDao;

    @Override
    public Object list(Object object) throws ServiceException {
        MemberQuery query = (MemberQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getEmp_id())) {
            map.put("emp_id", query.getEmp_id());
        }
        if (!StringUtil.isNullOrEmpty(query.getGd_type_id())) {
            map.put("gd_type_id", query.getGd_type_id());
        }

        List<EmpDianpu> list = memberDao.listDianPu(map);
        for(EmpDianpu empDianpu : list){
            if(!StringUtil.isNullOrEmpty(empDianpu.getMm_emp_cover())){
                if (empDianpu.getMm_emp_cover().startsWith("upload")) {
                    empDianpu.setMm_emp_cover(Constants.URL + empDianpu.getMm_emp_cover());
                }else {
                    empDianpu.setMm_emp_cover(Constants.QINIU_URL + empDianpu.getMm_emp_cover());
                }
            }
               if(!StringUtil.isNullOrEmpty(empDianpu.getCompany_pic())){
                   if (empDianpu.getCompany_pic().startsWith("upload")) {
                       empDianpu.setCompany_pic(Constants.URL + empDianpu.getCompany_pic());
                   }else {
                       empDianpu.setCompany_pic(Constants.QINIU_URL + empDianpu.getCompany_pic());
                   }
               }
        }
        return list;
    }


    @Override
    public Object update(Object object) {
        return null;
    }
}
