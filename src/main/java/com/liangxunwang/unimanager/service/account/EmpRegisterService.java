package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.ShenheTypeDao;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.ShenheTypeObj;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("empRegisterService")
public class EmpRegisterService implements SaveService {
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
        member.setMm_emp_cover(Constants.COVER_DEFAULT);//头像
        member.setMm_emp_password(new MD5Util().getMD5ofStr(member.getMm_emp_password()));//密码加密
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

}
