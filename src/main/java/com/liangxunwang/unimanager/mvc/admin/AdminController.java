package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
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
public class AdminController extends ControllerConstants {

    @Autowired
    @Qualifier("adminService")
    private ExecuteService adminExecuteService;

    @Autowired
    @Qualifier("adminService")
    private ListService adminListService;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @Autowired
    @Qualifier("adminService")
    private SaveService adminServiceSave;

    @RequestMapping("/changePass")
    @ResponseBody
    public String changePass(HttpSession session, String mm_manager_password, String mm_manager_id) throws Exception {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] params = new Object[]{mm_manager_id, mm_manager_password};
            adminExecuteService.execute(params);
            //日志记录
            logoService.save(new LogoObj("修改密码", manager.getMm_manager_id()));
            session.removeAttribute(ACCOUNT_KEY);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("/toChangePass")
    public String toChanagePass(){
        return "/admin/pass";
    }

    @RequestMapping("/admin/list")
    public String list(HttpSession session,ModelMap map, AdminQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) adminListService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //日志记录
        logoService.save(new LogoObj("查询管理员列表", manager.getMm_manager_id()));
        return "/admin/list";
    }


    //添加管理员
    @RequestMapping("/admin/addAdmin")
    @ResponseBody
    public String addPiao(HttpSession session,Admin admin){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            adminServiceSave.save(admin);
            //日志记录
            logoService.save(new LogoObj("添加管理员："+admin.getMm_manager_nickname(), manager.getMm_manager_id()));
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("MobileIsUse")){
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }

}
