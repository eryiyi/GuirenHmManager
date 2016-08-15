package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
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
@RequestMapping("/webvSuggestController")
public class WebvSuggestController extends ControllerConstants {

    @RequestMapping("toSuggest")
    public String toLogin(HttpSession session){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp == null){
            return "/webv/login";
        }else

        return "/webv/suggest";
    }


    @Autowired
    @Qualifier("suggestService")
    private SaveService levelServiceSave;

    @RequestMapping("/addSuggest")
    @ResponseBody
    public String empReg(HttpSession session, SuggestObj suggestObj){
        try {
            EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
            suggestObj.setMm_emp_id(emp.getMm_emp_id());
            levelServiceSave.save(suggestObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }

    }

}
