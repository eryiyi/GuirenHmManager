package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.query.FuwuQuery;
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
@RequestMapping("/fuwu")
public class FuwuController extends ControllerConstants {

    @Autowired
    @Qualifier("fuwuService")
    private ListService levelService;

    @Autowired
    @Qualifier("fuwuService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("fuwuService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("fuwuService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("fuwuService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, FuwuQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<FuwuObj> list = (List<FuwuObj>) levelService.list(query);
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看服务列表", manager.getMm_manager_id()));
        return "/fuwu/list";
    }

    @RequestMapping("add")
    public String add(ModelMap map, FuwuQuery query){
        return "/fuwu/addfuwu";
    }

    @RequestMapping("addFuwu")
    @ResponseBody
    public String addPiao(HttpSession session,FuwuObj level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(level);
        //日志记录
        logoService.save(new LogoObj("添加服务", manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_fuwu_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_fuwu_id);
        //日志记录
        logoService.save(new LogoObj("删除服务："+mm_fuwu_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        FuwuObj level = (FuwuObj) levelServiceSaveExe.execute(typeId);
        map.put("levelObj", level);
        //日志记录
        logoService.save(new LogoObj("编辑服务："+level.getMm_fuwu_nickname(), manager.getMm_manager_id()));
        return "/fuwu/editfuwu";
    }

    /**
     * 更新
     * @param level
     * @return
     */
    @RequestMapping("/editFuwu")
    @ResponseBody
    public String updateGoodsType(HttpSession session,FuwuObj level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(level);
            //日志记录
            logoService.save(new LogoObj("编辑服务："+level.getMm_fuwu_nickname(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
