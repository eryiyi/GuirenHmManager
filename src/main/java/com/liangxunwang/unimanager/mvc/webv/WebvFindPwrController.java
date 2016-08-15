package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvFindPwrController")
public class WebvFindPwrController extends ControllerConstants {

    @RequestMapping("toFindPwr")
    public String toLogin(){
        return "/webv/findpwr";
    }

    @Autowired
    @Qualifier("appEmpService")
    private UpdateService appEmpService;

    @RequestMapping("/findPwr")
    @ResponseBody
    public String empReg(Emp member){
        try {
            //修改用户密码
            appEmpService.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("has_none")){
                return toJSONString(ERROR_2);
            }else{
                return toJSONString(ERROR_1);
            }
        }

    }

}
