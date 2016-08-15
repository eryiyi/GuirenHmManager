package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.model.WeixinObj;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvWeixinController")
public class WebvWeixinController extends ControllerConstants {
    @Autowired
    @Qualifier("weixinService")
    private ListService levelService;

    @RequestMapping("toKefu")
    public String toLogin(HttpSession session,ModelMap map,KefuQuery query){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp == null){
            return "/webv/login";
        }else
        try {
            List<WeixinObj> list = (List<WeixinObj>) levelService.list(query);
            map.put("list", list);
            return "/webv/weixinkefu";
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return "/webv/login";
            }else{
                return "/webv/login";
            }
        }

    }

}
