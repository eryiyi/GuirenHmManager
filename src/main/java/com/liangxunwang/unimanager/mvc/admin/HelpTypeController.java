package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.HelpType;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.HelpTypeQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
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
@RequestMapping("/helpTypeController")
public class HelpTypeController extends ControllerConstants {

    @Autowired
    @Qualifier("helpTypeService")
    private ListService levelService;

    @Autowired
    @Qualifier("helpTypeService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("helpTypeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("helpTypeService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("helpTypeService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, HelpTypeQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(query.getHelp_type_f_id())){
            query.setHelp_type_f_id("0");
        }
        List<HelpType> list = (List<HelpType>) levelService.list(query);
        map.put("list", list);
        return "/helpType/list";
    }

    @RequestMapping("toAdd")
    public String add(ModelMap map, HelpTypeQuery query) throws Exception{
//        if(!StringUtil.isNullOrEmpty(query.getHelp_type_f_id()) && !"0".equals(query.getHelp_type_f_id())){
//            //顶级类型
//            HelpType helpType = (HelpType) levelServiceSaveExe.execute(query.getHelp_type_f_id());
//            map.put("helpType", helpType);
//        }
        query.setHelp_type_f_id("0");
        List<HelpType> list = (List<HelpType>) levelService.list(query);
        map.put("list", list);
        return "/helpType/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String addPiao(HttpSession session,HelpType helpType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(helpType);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String help_type_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(help_type_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toAdit(HttpSession session,ModelMap map, String help_type_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        HelpType helpType = (HelpType) levelServiceSaveExe.execute(help_type_id);
        map.put("helpType", helpType);

        HelpTypeQuery query = new HelpTypeQuery();
        if(StringUtil.isNullOrEmpty(query.getHelp_type_f_id())){
            query.setHelp_type_f_id("0");
        }
        List<HelpType> list = (List<HelpType>) levelService.list(query);
        map.put("list", list);
        return "/helpType/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,HelpType helpType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(helpType);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
