package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.dao.ReportDao;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.ReportObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppReportController extends ControllerConstants {

    @Autowired
    @Qualifier("reportService")
    private SaveService reportServiceSave;

    //保存举报信息
    @RequestMapping(value = "/saveReport", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getVipList(ReportObj reportObj){
        try {
            reportServiceSave.save(reportObj);
            return toJSONString(SUCCESS);
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
