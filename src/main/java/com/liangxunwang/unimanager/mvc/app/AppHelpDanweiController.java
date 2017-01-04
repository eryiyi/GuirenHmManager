package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.HelpDanwei;
import com.liangxunwang.unimanager.model.HelpType;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.HelpTypeQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppHelpDanweiController extends ControllerConstants {
    @Autowired
    @Qualifier("helpDanweiService")
    private ListService helpDanweiService;

    @RequestMapping(value = "/getHelpDanwei", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getHelpDanwei(){
        try {
            List<HelpDanwei> list = (List<HelpDanwei>) helpDanweiService.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
