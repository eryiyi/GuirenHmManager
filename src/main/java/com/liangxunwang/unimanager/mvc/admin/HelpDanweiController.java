package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.HelpDanwei;
import com.liangxunwang.unimanager.query.HelpTypeQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/helpDanweiController")
public class HelpDanweiController extends ControllerConstants {

    @Autowired
    @Qualifier("helpDanweiService")
    private ListService levelService;

    @Autowired
    @Qualifier("helpDanweiService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("helpDanweiService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("helpDanweiService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("helpDanweiService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, HelpTypeQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(query.getHelp_type_f_id())){
            query.setHelp_type_f_id("0");
        }
        List<HelpDanwei> list = (List<HelpDanwei>) levelService.list(query);
        map.put("list", list);
        return "/helpDanwei/list";
    }

    @RequestMapping("toAdd")
    public String add(ModelMap map) throws Exception{
        List<HelpDanwei> list = (List<HelpDanwei>) levelService.list("");
        map.put("list", list);
        return "/helpDanwei/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String addPiao(HttpSession session,HelpDanwei helpType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(helpType);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String help_danwei_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(help_danwei_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toAdit(HttpSession session,ModelMap map, String help_danwei_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        HelpDanwei helpType = (HelpDanwei) levelServiceSaveExe.execute(help_danwei_id);
        map.put("helpType", helpType);
        return "/helpType/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,HelpDanwei helpType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(helpType);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
