package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.query.BaseAreaQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class IndexController extends ControllerConstants {
    @Autowired
    @Qualifier("indexService")
    private ListService indexListService;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("/index")
    public String index(HttpSession session){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if (admin != null){
            return "/index";
        }
        return "/adminLogin";
    }

    @RequestMapping("/main")
    public String left(){

        return "/index";
    }

    @RequestMapping("/mainPage")
    public String mainPage(HttpSession session,ModelMap map,BaseAreaQuery query){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if(admin != null){
            //管理员类别
            map.put("mm_manager_type", admin.getMm_manager_type());
        }
        List<Object> list = (List<Object>) indexListService.list(query);
        //总共会员数量
        Long memberCount = (Long) list.get(0);
        map.put("memberCount", memberCount);

        //如果是顶级管理员查询举报未处理的数量
        Long countReport = (Long) list.get(1);
        map.put("countReport", countReport);
        map.put("countEmpDay", (Long) list.get(2));
        return "/main";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Enumeration en = session.getAttributeNames();
        while (en.hasMoreElements()) {
            session.removeAttribute((String)en.nextElement());
        }
        return "redirect:/";
    }



}
