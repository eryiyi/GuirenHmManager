package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.model.SuggestObj;
import com.liangxunwang.unimanager.mvc.vo.SuggestVO;
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
@RequestMapping("/suggest")
public class SuggestController extends ControllerConstants {

    @Autowired
    @Qualifier("suggestService")
    private ListService levelService;

    @Autowired
    @Qualifier("suggestService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("suggestService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("suggestService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<SuggestVO> list = (List<SuggestVO>) levelService.list("");
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看建议列表", manager.getMm_manager_id()));
        return "/suggest/list";
    }



    @RequestMapping("addSuggest")
    @ResponseBody
    public String addPiao(HttpSession session,SuggestObj level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(level);
        //日志记录
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_suggest_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_suggest_id);
        //日志记录
        logoService.save(new LogoObj("删除建议："+mm_suggest_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

}
