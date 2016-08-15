package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.ExecuteService;
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
@Service("appEmpBaiduService")
public class AppEmpBaiduService implements  UpdateService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object update(Object object) {
        Object[] params = (Object[]) object;
//        {id, userId, channelId, type}
        String id = (String) params[0];
        String userId = (String) params[1];
        String channelId = (String) params[2];
        String type = (String) params[3];
        empDao.updatePushId(id, userId, channelId, type);
        return null;
    }
}
