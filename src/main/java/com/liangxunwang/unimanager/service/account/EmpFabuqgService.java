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
 * 批量处理用户发布求购
 */
@Service("empFabuqgService")
public class EmpFabuqgService implements ExecuteService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        String type = (String) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            for(int i=0;i<arrs.length;i++){
                //type 0bu允许  1允许
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("mm_emp_id", arrs[i]);
                map.put("is_fabuqiugou", type);
                empDao.updateFabuqg(map);
            }
        }
        return null;
    }




}
