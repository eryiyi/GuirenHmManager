package com.liangxunwang.unimanager.mvc.app;

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
public class AppHelpTypeController extends ControllerConstants {
    @Autowired
    @Qualifier("helpTypeService")
    private ListService helpTypeService;

    //帮忙类型列表
    @RequestMapping(value = "/getHelpTypes", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getHelpTypes(HelpTypeQuery query){
        try {
            List<HelpType> list = (List<HelpType>) helpTypeService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
