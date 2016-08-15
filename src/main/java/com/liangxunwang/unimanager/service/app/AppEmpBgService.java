package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.baiduyun.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baiduyun.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baiduyun.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baiduyun.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baiduyun.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baiduyun.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baiduyun.log.YunLogEvent;
import com.liangxunwang.unimanager.baiduyun.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.CommonUtil;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 */
@Service("appEmpBgService")
public class AppEmpBgService implements  UpdateService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Override
    public Object update(Object object) throws ServiceException {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            empDao.updateBgMine(emp);
        }
        return null;
    }
}
