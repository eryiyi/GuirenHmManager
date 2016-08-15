package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.model.ShenheTypeObj;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/shenheType")
public class ShenheTypeController extends ControllerConstants {

    @Autowired
    @Qualifier("shenheTypeService")
    private ListService feiyongServiceList;

    @Autowired
    @Qualifier("shenheTypeService")
    private SaveService feiyongServiceSave;

    @Autowired
    @Qualifier("shenheTypeService")
    private UpdateService feiyongServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("add")
    public String add(ModelMap map){
        List<ShenheTypeObj> list = (List<ShenheTypeObj>) feiyongServiceList.list("");
       if(list != null && list.size() > 0){
           map.put("shenhe", list.get(0));
       }
        return "/shenhe/shenhetype";
    }

    @RequestMapping("update")
    @ResponseBody
    public String addAboutUs(HttpSession session,ShenheTypeObj aboutUs){
        List<ShenheTypeObj> list = (List<ShenheTypeObj>) feiyongServiceList.list("");
        if(list != null && list.size() > 0){
            //需要跟新
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            try {
                feiyongServiceUpdate.update(aboutUs);
                //日志记录
                logoService.save(new LogoObj("更新审核方式", manager.getMm_manager_id()));
                return toJSONString(SUCCESS);
            }catch (ServiceException e){
                return toJSONString(ERROR_1);
            }
        }else {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            feiyongServiceSave.save(aboutUs);
            //日志记录
            logoService.save(new LogoObj("保存审核方式", manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }
    }


}
