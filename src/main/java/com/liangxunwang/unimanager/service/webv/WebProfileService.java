package com.liangxunwang.unimanager.service.webv;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.ShenheTypeDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.ShenheTypeObj;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Service("webProfileService")
public class WebProfileService implements SaveService,UpdateService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao memberDao;

    @Autowired
    @Qualifier("shenheTypeDao")
    private ShenheTypeDao shenheTypeDao;

    @Override
    public Object save(Object object) {
        Emp member = (Emp) object;
        Emp checkMember = memberDao.findByMobile(member.getMm_emp_mobile());
        if (checkMember != null){
            throw new ServiceException("MobileIsUse");
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
        member.setIs_upate_profile("0");//是否补充资料 默认否


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
            member.setIscheck("1");//是否审核  0默认否  1已审核
        }

        try {
            memberDao.save(member);
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
}
