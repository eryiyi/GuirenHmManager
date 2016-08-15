package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.FuwuQuery;
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
public class AppAboutUsController extends ControllerConstants {

    @Autowired
    @Qualifier("aboutUsService")
    private ListService feiyongServiceList;

    //关于我们
    @RequestMapping(value = "/getAboutUs", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAboutUs(){
        try {
            List<AboutUs> list = (List<AboutUs>) feiyongServiceList.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
