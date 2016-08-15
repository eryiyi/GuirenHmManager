package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvUpdatePwrController")
public class WebvUpdatePwrController extends ControllerConstants {

    @RequestMapping("toFindPwr")
    public String toLogin(HttpSession session){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp == null){
            return "/webv/login";
        }else
        return "/webv/updatePwr";
    }

    @Autowired
    @Qualifier("appEmpService")
    private UpdateService appEmpService;

    @RequestMapping("/findPwr")
    @ResponseBody
    public String empReg(HttpSession session,Emp member){
        try {
            EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
            member.setMm_emp_id(emp.getMm_emp_id());
            //先判断原始密码是否正确
            //这个是原始密码 不是手机号
            if (!new MD5Util().getMD5ofStr(member.getMm_emp_mobile()).equals(emp.getMm_emp_password())){
                return toJSONString(ERROR_2);
            }
            //修改用户密码
            appEmpService.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }

    }

}
