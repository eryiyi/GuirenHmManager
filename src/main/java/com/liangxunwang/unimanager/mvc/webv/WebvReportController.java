package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.ReportObj;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvReportController")
public class WebvReportController extends ControllerConstants {

    @RequestMapping("toReport")
    public String toReport(ModelMap map,ReportObj suggestObj,HttpSession session){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp != null){
            map.put("mm_msg_id", suggestObj.getMm_msg_id());
            map.put("mm_emp_id", emp.getMm_emp_id());//举报者
            map.put("mm_emp_id_t", suggestObj.getMm_emp_id());//被举报的人
            return "/webv/report";
        }else {
            return "/webv/login";
        }
    }

    @Autowired
    @Qualifier("reportService")
    private SaveService reportServiceSave;

    @RequestMapping("/addReport")
    @ResponseBody
    public String addReport(HttpSession session, ReportObj reportObj){
        try {
            EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
            if(emp != null){
                reportServiceSave.save(reportObj);
                return toJSONString(SUCCESS);
            }else {
                return toJSONString(ERROR_9);
            }

        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }

    }

}
