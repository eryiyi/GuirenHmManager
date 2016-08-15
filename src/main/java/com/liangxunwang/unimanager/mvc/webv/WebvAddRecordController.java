package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
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
@RequestMapping("/webvAddRecordController")
public class WebvAddRecordController extends ControllerConstants {

    @RequestMapping("toAddRecord")
    public String toAddRecord( ModelMap map, HttpSession session){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp == null){
            return "/webv/login";
        }else{
            map.put("emp", emp);
            return "/webv/addRecord";
        }
    }

    @Autowired
    @Qualifier("appRecordService")
    private SaveService recordSaveService;

    @RequestMapping("/addRecord")
    @ResponseBody
    public String addRecord(HttpSession session, Record record){
        try {
            EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
            if(emp != null && !StringUtil.isNullOrEmpty(emp.getMm_emp_id())){
                record.setMm_emp_id(emp.getMm_emp_id());
                recordSaveService.save(record);//保存信息
                return toJSONString(SUCCESS);
            }else {
                return toJSONString(ERROR_9);
            }

        }catch (ServiceException e){
            if (e.getMessage().equals("HAS_PUBLISH")){
                return toJSONString(ERROR_2);
            }else if(e.getMessage().equals("HAS_FULL")){
                return toJSONString(ERROR_3);
            }else if(e.getMessage().equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }
            else{
                return toJSONString(ERROR_1);
            }
        }
    }

}
