package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.query.FeiyongQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/aboutUs")
public class AboutUsController extends ControllerConstants {

    @Autowired
    @Qualifier("aboutUsService")
    private ListService feiyongServiceList;

    @Autowired
    @Qualifier("aboutUsService")
    private SaveService feiyongServiceSave;

    @Autowired
    @Qualifier("aboutUsService")
    private UpdateService feiyongServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;


    @RequestMapping("add")
    public String add(ModelMap map){
        List<AboutUs> list = (List<AboutUs>) feiyongServiceList.list("");
       if(list != null && list.size() > 0){
           map.put("aboutUs", list.get(0));
       }
        return "/about/addAboutUs";
    }

    @RequestMapping("addAboutUs")
    @ResponseBody
    public String addAboutUs(HttpSession session,AboutUs aboutUs){
        List<AboutUs> list = (List<AboutUs>) feiyongServiceList.list("");
        if(list != null && list.size() > 0){
            //需要跟新
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            try {
                feiyongServiceUpdate.update(aboutUs);
                //日志记录
                logoService.save(new LogoObj("更新关于我们", manager.getMm_manager_id()));
                return toJSONString(SUCCESS);
            }catch (ServiceException e){
                return toJSONString(ERROR_1);
            }
        }else {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            feiyongServiceSave.save(aboutUs);
            //日志记录
            logoService.save(new LogoObj("保存关于我们", manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }
    }


}
