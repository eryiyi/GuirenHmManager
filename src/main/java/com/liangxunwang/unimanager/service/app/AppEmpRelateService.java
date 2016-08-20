package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.baiduyun.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baiduyun.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baiduyun.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baiduyun.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baiduyun.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baiduyun.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baiduyun.log.YunLogEvent;
import com.liangxunwang.unimanager.baiduyun.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.AdDao;
import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.EmpRelateDao;
import com.liangxunwang.unimanager.dao.RelateDao;
import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.EmpRelateObj;
import com.liangxunwang.unimanager.model.Relate;
import com.liangxunwang.unimanager.mvc.vo.EmpRelateObjVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.EmpRelateQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.CommonUtil;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("appEmpRelateService")
public class AppEmpRelateService implements ListService,UpdateService,SaveService,DeleteService{
    private static Logger logger = LogManager.getLogger(AppEmpRelateService.class);
    @Autowired
    @Qualifier("empRelateDao")
    private EmpRelateDao empRelateDao;

    @Override
    public Object list(Object object) throws ServiceException {
        EmpRelateQuery query = (EmpRelateQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id1())){
            map.put("mm_emp_id1", query.getMm_emp_id1());
        }
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id2())){
            map.put("mm_emp_id2", query.getMm_emp_id2());
        }
        if(!StringUtil.isNullOrEmpty(query.getState())){
            map.put("state", query.getState());
        }
        List<EmpRelateObjVO> lists = empRelateDao.lists(map);
        for(EmpRelateObjVO adObj:lists){
            if(!StringUtil.isNullOrEmpty(adObj.getMm_emp_cover())){
                if(adObj.getMm_emp_cover().startsWith("upload")){
                    adObj.setMm_emp_cover(Constants.URL + adObj.getMm_emp_cover());
                }else {
                    adObj.setMm_emp_cover(Constants.QINIU_URL + adObj.getMm_emp_cover());
                }
            }
        }
        return lists;
    }

    @Autowired
    @Qualifier("relateDao")
    private RelateDao relateDao;

    //更新
    @Override
    public Object update(Object object) {
        EmpRelateObj empRelateObj =  (EmpRelateObj) object;
        empRelateDao.update(empRelateObj);
        EmpRelateObj empRelateObj1 = empRelateDao.findById(empRelateObj.getEmp_relate_id());//根据id查询存在该拜见关系
        if(empRelateObj1 != null){
                String mm_emp_id1 = empRelateObj1.getMm_emp_id1();
                String mm_emp_id2 = empRelateObj1.getMm_emp_id2();

                empRelateObj1.setEmp_relate_id(UUIDFactory.random());
                empRelateObj1.setMm_emp_id1(mm_emp_id2);
                empRelateObj1.setMm_emp_id2(mm_emp_id1);
                empRelateObj1.setState("1");
                EmpRelateObj empRelateObj2 =  empRelateDao.find(empRelateObj1);//根据两个人id查询是否存在关系
            if(empRelateObj2 == null){
                empRelateDao.save(empRelateObj1);

                //与我相关
                Relate relate1 = new Relate();
                relate1.setId(UUIDFactory.random());
                relate1.setEmpId(empRelateObj1.getMm_emp_id1());
                relate1.setEmpTwoId(empRelateObj1.getMm_emp_id2());
                relate1.setRecordId(empRelateObj1.getEmp_relate_id());
                relate1.setTypeId("7");
                relate1.setDateline(System.currentTimeMillis()+"");
                relate1.setCont("我们已经结交，很高兴认识你");
                relateDao.save(relate1);
                Emp pushMember =  memberDao.findById(empRelateObj1.getMm_emp_id2());
                String pushId =pushMember.getPushId();
                String type = pushMember.getDeviceType();
                if(!StringUtil.isNullOrEmpty(pushId) && !StringUtil.isNullOrEmpty(type)){
                    pushRelate(pushId, type, "我们已经结交，很高兴认识你");
                }
            }else{
                //说明已经存在了 需要跟新
                empRelateDao.update(empRelateObj2);
                //与我相关
                Relate relate1 = new Relate();
                relate1.setId(UUIDFactory.random());
                relate1.setEmpId(empRelateObj2.getMm_emp_id1());
                relate1.setEmpTwoId(empRelateObj2.getMm_emp_id2());
                relate1.setRecordId(empRelateObj2.getEmp_relate_id());
                relate1.setTypeId("7");
                relate1.setDateline(System.currentTimeMillis()+"");
                relate1.setCont("我们已经结交，很高兴认识你");
                relateDao.save(relate1);
                Emp pushMember =  memberDao.findById(empRelateObj2.getMm_emp_id2());
                String pushId =pushMember.getPushId();
                String type = pushMember.getDeviceType();
                if(!StringUtil.isNullOrEmpty(pushId) && !StringUtil.isNullOrEmpty(type)){
                    pushRelate(pushId, type, "我们已经结交，很高兴认识你");
                }
            }
        }
        return null;
    }

    @Autowired
    @Qualifier("empDao")
    private EmpDao memberDao;

    @Override
    public Object save(Object object) throws ServiceException {
        EmpRelateObj empRelateObj = (EmpRelateObj) object;
        //先查询 是否已经存在两个人的关系了
        EmpRelateObj empRelateObj1 = empRelateDao.find(empRelateObj);
        if(empRelateObj1 != null ){
            //说明存在
            throw new ServiceException("has_exist");
        }
        empRelateObj.setEmp_relate_id(UUIDFactory.random());
        empRelateObj.setState("0");
        empRelateDao.save(empRelateObj);
        //与我相关
        Relate relate1 = new Relate();
        relate1.setId(UUIDFactory.random());
        relate1.setEmpId(empRelateObj.getMm_emp_id1());
        relate1.setEmpTwoId(empRelateObj.getMm_emp_id2());
        relate1.setRecordId(empRelateObj.getEmp_relate_id());
        relate1.setTypeId("9");
        relate1.setDateline(System.currentTimeMillis()+"");
        relate1.setCont("我来拜见您，认识下吧。");
        relateDao.save(relate1);
        Emp pushMember =  memberDao.findById(empRelateObj.getMm_emp_id2());
        String pushId =pushMember.getPushId();
        String type = pushMember.getDeviceType();
        if(!StringUtil.isNullOrEmpty(pushId) && !StringUtil.isNullOrEmpty(type)){
            pushRelate(pushId, type, " 我来拜见您，认识下吧。");
        }
        return null;
    }

    private void pushRelate(final String pushId, final String type, final String content){
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

                    logger.info("开始调用百度推送接口");
                    // 5. 调用pushMessage接口
                    PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

                    logger.info("推送成功----"+response.getSuccessAmount());
                    // 6. 认证推送成功
                    System.out.println("push amount : " + response.getSuccessAmount());

                } catch (ChannelClientException e) {
                    // 处理客户端错误异常
                    e.printStackTrace();
                    logger.info("处理客户端异常"+e.getMessage());
                } catch (ChannelServerException e) {
                    // 处理服务端错误异常
                    System.out.println(String.format(
                            "request_id: %d, error_code: %d, error_message: %s",
                            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
                }
            }
        });
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        EmpRelateObj empRelateObj = (EmpRelateObj) object;
        empRelateDao.delete(empRelateObj.getEmp_relate_id());
        return null;
    }
}
