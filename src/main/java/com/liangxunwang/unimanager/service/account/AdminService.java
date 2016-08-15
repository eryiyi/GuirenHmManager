package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/12.
 */
@Service("adminService")
public class AdminService implements ExecuteService,ListService,SaveService{

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        Object[] params = (Object[]) object;
        String userId = (String) params[0];
        String userPass = (String) params[1];
        adminDao.updatePass(userId, userPass);

        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        AdminQuery query = (AdminQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getMm_manager_is_use())) {
            map.put("mm_manager_is_use", query.getMm_manager_is_use());
        }

        List<AdminVO> lists = adminDao.lists(map);
        long count = adminDao.count(map);

        return new Object[]{lists, count};
    }


    @Override
    public Object save(Object object) throws ServiceException {
        Admin admin = (Admin) object;
        //先查看手机号是否已经存在了
        Admin admin1 = adminDao.findByMobile(admin.getMm_manager_mobile());
        if (admin1 != null){
            throw new ServiceException("MobileIsUse");
        }
        adminDao.add(admin);
        return null;
    }
}
