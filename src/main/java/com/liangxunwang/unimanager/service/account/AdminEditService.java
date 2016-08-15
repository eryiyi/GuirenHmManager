package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.RoleDao;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/12.
 */
@Service("adminEditService")
public class AdminEditService implements ExecuteService,UpdateService{

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        String userId = (String) object;
        AdminVO admin = adminDao.findById(userId);
        if(admin != null){
            String permission = null;
            //查询权限
            if ("all".equals(admin.getPermissions())){
                permission = "all";
            }
            else {
                if (!StringUtil.isNullOrEmpty(admin.getPermissions())){
                    Role role = roleDao.find(admin.getPermissions());
                    permission = role.getPermissions();
                }
            }
            //查询角色
            Role role = null;
            if("all".equals(admin.getPermissions())){
                //是全部权限
            }else {
                role = roleDao.find(admin.getPermissions());
            }
            return new Object[]{admin, permission, role};
        }else {
            return null;
        }
    }


    @Override
    public Object update(Object object) {
        if (object instanceof Object[]){
            Object[] params = (Object[]) object;
            String mm_manager_id = (String) params[0];
            String mm_manager_is_use = (String) params[1];
            adminDao.updateStatus(mm_manager_id, mm_manager_is_use);
        }
        return null;
    }
}
