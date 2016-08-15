package com.liangxunwang.unimanager.service.app;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.liangxunwang.unimanager.baiduyun.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baiduyun.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baiduyun.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baiduyun.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baiduyun.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baiduyun.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baiduyun.log.YunLogEvent;
import com.liangxunwang.unimanager.baiduyun.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.huanxin.httpclient.utils.ChatUtils;
import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.lang.Long.parseLong;

/**
 * Created by zhl on 2015/1/29.
 */
@Service("memberRegisterService")
public class MemberRegisterService implements SaveService,UpdateService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao memberDao;

    @Autowired
    @Qualifier("shenheTypeDao")
    private ShenheTypeDao shenheTypeDao;

    @Autowired
    @Qualifier("yaoqingCardDao")
    private YaoqingCardDao yaoqingCardDao;

    @Autowired
    @Qualifier("xixunDao")
    private XixunDao xixunDao;

    @Autowired
    @Qualifier("jifenDao")
    private JifenDao jifenDao;


    @Autowired
    @Qualifier("relateDao")
    private RelateDao relateDao;

    @Override
    public Object save(Object object) {
        Emp member = (Emp) object;
        Emp checkMember = memberDao.findByMobile(member.getMm_emp_mobile());
        if (checkMember != null){
            throw new ServiceException("MobileIsUse");
        }
        //查询邀请码是否存在并且没有使用
        Map<String, Object> mapYq = new HashMap<String, Object>();
        mapYq.put("guiren_card_num", member.getGuiren_card_num());
        YaoqingCard yaoqingCard = yaoqingCardDao.findById(mapYq);//根据邀请码查询是否存在
        if(yaoqingCard == null || "1".equals(yaoqingCard.getIs_use())){
            //已经使用了 或者不存在该邀请码
            throw new ServiceException("CardIsError");
        }

        if(yaoqingCard != null && !StringUtil.isNullOrEmpty(yaoqingCard.getMm_emp_id()) && !"0".equals(yaoqingCard.getMm_emp_id())){
            //存在邀请人， 查询该邀请人的top-number
            //根据用户id查询用户数据
            EmpVO empVO = memberDao.findById(yaoqingCard.getMm_emp_id());
            Map<String, Object> mapTopNumber = new HashMap<String, Object>();
            if(empVO != null && !StringUtil.isNullOrEmpty(empVO.getTop_number())){
                mapTopNumber.put("top_number", empVO.getTop_number());
                List<EmpVO> lists = memberDao.listtopnumber(mapTopNumber);
                if(lists != null && lists.size() >0){
                    EmpVO empVO1 = lists.get(0);//最大的值top_number
                    if(empVO.getTop_number().length() == empVO1.getTop_number().length()){
                        //说明需要增加 ‘0001’
                        member.setTop_number(empVO1.getTop_number() + "0001");
                    }else{
                        member.setTop_number((new BigInteger(empVO1.getTop_number()).add(new BigInteger("1")).toString()));
                    }

                }
            }else {
                //当前注册会员是顶级会员  没有邀请人 查询0001 到哪个了
                List<EmpVO> lists = memberDao.listtopnumber2(mapTopNumber);
                if(lists != null && lists.size() >0){
                    EmpVO empVO1 = lists.get(0);//最大的值top_number
                    String numberStr = (new BigInteger(empVO1.getTop_number()).add(new BigInteger("1"))).toString();
                    member.setTop_number(numberStr);
                }
            }
        }else {
            //当前注册会员是顶级会员  没有邀请人 查询0001 到哪个了
            Map<String, Object> mapTopNumber = new HashMap<String, Object>();
            List<EmpVO> lists = memberDao.listtopnumber2(mapTopNumber);
            if(lists != null && lists.size() >0){
                EmpVO empVO1 = lists.get(0);//最大的值top_number
                member.setTop_number((new BigInteger(empVO1.getTop_number()).add(new BigInteger("1"))).toString());
            }
        }
        member.setMm_emp_id(UUIDFactory.random());//设置ID
        member.setMm_emp_regtime(DateUtil.getDateAndTime());//时间戳
        if(!StringUtil.isNullOrEmpty(member.getMm_emp_cover())){
            //
        }else{
            member.setMm_emp_cover(Constants.COVER_DEFAULT);//头像
        }
        member.setMm_emp_password(new MD5Util().getMD5ofStr(member.getMm_emp_password()));//密码加密
        member.setIs_login("0");//允许登陆 默认0允许
        member.setIs_use("0");//是否禁用 0默认否
        member.setIscheck("1");
        member.setIs_upate_profile("0");//是否补充资料 默认否
        member.setHxusername(System.currentTimeMillis()+member.getMm_emp_mobile());
        //判断审核方式
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShenheTypeObj> listsShenheType = shenheTypeDao.lists(map);
        if(listsShenheType != null && listsShenheType.size()>0){
            ShenheTypeObj shenheTypeObj = listsShenheType.get(0);
            if(shenheTypeObj != null){
                if("0".equals(shenheTypeObj.getMm_shenhe_type())){
                    //自动审核
                    member.setIscheck("1");//是否审核  0默认否  1已审核
                }else{
                    //需要管理员手动审核
                    member.setIscheck("0");//是否审核  0默认否  1已审核
                }
            }

        }else {
            member.setIscheck("0");//是否审核  0默认否  1已审核
        }
        try {
            memberDao.save(member);
            Boolean suc= ChatUtils.register(member.getHxusername(), "111111");//注册环信
            //更新邀请码 更新为已用
            yaoqingCard.setIs_use("1");
            yaoqingCard.setY_mm_emp_id(member.getMm_emp_id());
            yaoqingCardDao.update(yaoqingCard);
            //发喜讯
            XixunObj xixunObj = new XixunObj();
            xixunObj.setMm_emp_id(member.getMm_emp_id());
            xixunObj.setMm_emp_nickname(member.getMm_emp_nickname());
            xixunObj.setGuiren_xixun_id(UUIDFactory.random());
            xixunObj.setDateline(System.currentTimeMillis() + "");
            xixunObj.setGuiren_xixun_title("欢迎来自"+ member.getProvinceName()+member.getCityName()+member.getCountryName() + "的" + member.getMm_emp_nickname()+"加入我们贵人网");
            xixunDao.save(xixunObj);
            //插入积分表
            EmpCount  empCount = new EmpCount();
            empCount.setMm_emp_id(member.getMm_emp_id());
            empCount.setEmp_count_number(0);
            jifenDao.save(empCount);
            //邀请人积分加一
            if(!StringUtil.isNullOrEmpty(yaoqingCard.getMm_emp_id()) && !"0".equals(yaoqingCard.getMm_emp_id())){
                EmpCount empCount1 = new EmpCount();
                empCount1.setMm_emp_id(yaoqingCard.getMm_emp_id());//邀请码所有者的id
                jifenDao.update(empCount1);
                if("0".equals(member.getIscheck())){
                    //通知邀请人审核
                    EmpVO empVO = memberDao.findById(yaoqingCard.getMm_emp_id());

                    //添加与我相关
                    Relate relate1 = new Relate();
                    relate1.setId(UUIDFactory.random());
                    relate1.setEmpId(member.getMm_emp_id());
                    relate1.setEmpTwoId(empVO.getMm_emp_id());
                    relate1.setRecordId(member.getMm_emp_id());
                    relate1.setTypeId("8");
                    relate1.setDateline(System.currentTimeMillis()+"");
                    relate1.setCont("贵人，您好，您邀请的好友"+ member.getMm_emp_nickname() + "以注册成功，期待您的审核。");
                    relateDao.save(relate1);

                    //百度推送通知邀请人审核
                    String pushId =empVO.getPushId();
                    String type = empVO.getDeviceType();
                    if(!StringUtil.isNullOrEmpty(pushId) && !StringUtil.isNullOrEmpty(type)){
                        pushBaiDuYun(pushId,type, "贵人，您好，您邀请的好友"+ member.getMm_emp_nickname() + "以注册成功，期待您的审核。");
                    }
                }
            }

        }catch (Exception e){
            throw new ServiceException(Constants.SAVE_ERROR);
        }
        return member;
    }

    //完善个人资料 app
    @Override
    public Object update(Object object) {
        Emp member = (Emp) object;
        member.setIs_upate_profile("1");
        try {
            memberDao.updateProfileMember(member);
        }catch (Exception e){
            throw new ServiceException(Constants.SAVE_ERROR);
        }
        return member;
    }


    private void pushBaiDuYun(final String pushId, final String type, final String content){
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
