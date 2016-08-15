package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.SuggestVO;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * vip到期通知语句设置
 */
@Controller
@RequestMapping("vipEndTimeController")
public class VipEndTimeController extends ControllerConstants {

    @Autowired
    @Qualifier("vipEndTimeService")
    private ListService vipEndTimeServiceList;

    @Autowired
    @Qualifier("vipEndTimeService")
    private ExecuteService vipEndTimeServiceExe;

    @Autowired
    @Qualifier("vipEndTimeService")
    private UpdateService vipEndTimeServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<VipEndTime> list = (List<VipEndTime>) vipEndTimeServiceList.list("");
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看vip到期常用语列表", manager.getMm_manager_id()));
        return "/vipendtime/list";
    }


    @RequestMapping("edit")
    public String toEdit(@RequestParam String id, ModelMap map){
        VipEndTime vipEndTime = null;
        try {
            vipEndTime = (VipEndTime) vipEndTimeServiceExe.execute(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("vipEndTime", vipEndTime);
        return "/vipendtime/edit";
    }


    /**
     * 修改到期常用语
     * @param vipEndTime
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public String update(VipEndTime vipEndTime){
        vipEndTimeServiceUpdate.update(vipEndTime);
        return toJSONString(SUCCESS);
    }

}
