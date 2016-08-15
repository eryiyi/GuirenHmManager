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
@Service("appEmpYqService")
public class AppEmpYqService implements  UpdateService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;


    @Override
    public Object update(Object object) throws ServiceException {
        if (object instanceof Emp){
            Emp emp = (Emp) object;
            empDao.updateEmpCheck(emp);
            if("1".equals(emp.getIscheck())){
                //通过审核
                Emp pushMember = empDao.findById(emp.getMm_emp_id());
                String pushId =pushMember.getPushId();
                String type = pushMember.getDeviceType();
                if(!StringUtil.isNullOrEmpty(pushId) && !StringUtil.isNullOrEmpty(type)){
                    pushZan(pushId, type, "您注册的贵人账号已通过审核，请登录。");
                }
            }else{
                //审核不通过
                Emp pushMember = empDao.findById(emp.getMm_emp_id());
                String pushId =pushMember.getPushId();
                String type = pushMember.getDeviceType();
                if(!StringUtil.isNullOrEmpty(pushId) && !StringUtil.isNullOrEmpty(type)){
                    pushZan(pushId, type, "您注册的贵人账号没通过审核，请联系推荐人！");
                }
            }
        }
        return null;
    }

    private void pushZan(final String pushId, final String type, final String content){
        CommonUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                ChannelKeyPair pair = null;
                if (type.equals("3")) {
                    pair = new ChannelKeyPair(Constants.API_KEY, Constants.SECRET_KEY);
                } else {
                    pair = new ChannelKeyPair(Constants.IOS_API_KEY, Constants.IOS_SECRET_KEY);
                }

                // 2. 创建BaiduChannelClient对象实例
                BaiduChannelClient channelClient = new BaiduChannelClient(pair);
                // 3. 若要了解交互细节，请注册YunLogHandler类
                channelClient.setChannelLogHandler(new YunLogHandler() {
                    @Override
                    public void onHandle(YunLogEvent event) {
                        System.out.println(event.getMessage());
                    }
                });
                try {
                    // 4. 创建请求类对象
                    // 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
                    PushUnicastMessageRequest request = new PushUnicastMessageRequest();
                    request.setDeviceType(Integer.parseInt(type));
                    if (type.equals("4")) {//如果是苹果手机端要设置这个
                        request.setDeployStatus(2);
                    }
//            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android  4:ios 5:wp
//            request.setChannelId(Long.parseLong(pushId));
//            request.setChannelId(5100663888284228047L);
                    request.setUserId(pushId);

                    request.setMessageType(1);
                    request.setMessage("{\"title\":\"提醒\",\"description\":\"" + content + "\",\"custom_content\": {\"type\":\"2\"}}");

                    // 5. 调用pushMessage接口
                    PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

                    // 6. 认证推送成功
                    System.out.println("push amount : " + response.getSuccessAmount());

                } catch (ChannelClientException e) {
                    // 处理客户端错误异常
                    e.printStackTrace();
                } catch (ChannelServerException e) {
                    // 处理服务端错误异常
                    System.out.println(String.format(
                            "request_id: %d, error_code: %d, error_message: %s",
                            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
                }
            }
        });
    }
}
