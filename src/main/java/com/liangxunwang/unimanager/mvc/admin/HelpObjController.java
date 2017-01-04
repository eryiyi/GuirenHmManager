package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.HelpObj;
import com.liangxunwang.unimanager.query.HelpObjQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 */
@Controller
@RequestMapping("/helpObjController")
public class HelpObjController extends ControllerConstants {
    @Autowired
    @Qualifier("helpObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("helpObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("helpObjService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("helpObjService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(ModelMap map, HelpObjQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[])  levelService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/helpObj/list";
    }


    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String help_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(help_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toUpdateType(HttpSession session,ModelMap map, String help_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        HelpObj helpObj = (HelpObj) levelServiceSaveExe.execute(help_id);
        map.put("helpObj", helpObj);
        return "/helpObj/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String updateGoodsType(HttpSession session,HelpObj helpObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(helpObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
