package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvAboutUsController")
public class WebvAboutUsController extends ControllerConstants {

    @Autowired
    @Qualifier("aboutUsService")
    private ListService feiyongServiceList;


    @RequestMapping("toAboutUs")
    public String toLogin(ModelMap map){
        List<AboutUs> list = (List<AboutUs>) feiyongServiceList.list("");
        if(list != null && list.size() > 0){
            map.put("aboutUs", list.get(0));
        }
        return "/webv/aboutus";
    }



}
