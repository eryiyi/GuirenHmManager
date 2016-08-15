package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.EmpAdObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.EmpAdVO;
import com.liangxunwang.unimanager.query.EmpAdQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
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
@RequestMapping("/empAd")
public class EmpAdController extends ControllerConstants {

    @Autowired
    @Qualifier("empAdService")
    private ListService levelService;

    @Autowired
    @Qualifier("empAdService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("empAdService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("empAdService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, EmpAdQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<EmpAdVO> list = (List<EmpAdVO>) levelService.list(query);
        map.put("list", list);
        map.put("mm_emp_id", query.getMm_emp_id());
        //日志记录
        logoService.save(new LogoObj("查看会员广告图列表", manager.getMm_manager_id()));
        return "/empAd/list";
    }

    @RequestMapping("toAdd")
    public String add(ModelMap map, EmpAdQuery query){
        map.put("mm_emp_id",query.getMm_emp_id());
        return "/empAd/addEmpAd";
    }

    @RequestMapping("addEmpAd")
    @ResponseBody
    public String addEmpAd(HttpSession session,EmpAdObj empAdObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(empAdObj);
        //日志记录
        logoService.save(new LogoObj("添加会员广告图："+empAdObj.getMm_emp_id(), manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_emp_ad_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_emp_ad_id);
        //日志记录
        logoService.save(new LogoObj("删除会员广告图："+mm_emp_ad_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }


}
