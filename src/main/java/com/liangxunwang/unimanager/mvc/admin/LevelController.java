package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/level")
public class LevelController extends ControllerConstants {

    @Autowired
    @Qualifier("levelService")
    private ListService levelService;

    @Autowired
    @Qualifier("levelService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("levelService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("levelService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("levelService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, LevelQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<Level> list = (List<Level>) levelService.list(query);
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看等级列表", manager.getMm_manager_id()));
        return "/level/list";
    }

    @RequestMapping("add")
    public String add(ModelMap map, LevelQuery query){
        return "/level/addlevel";
    }

    @RequestMapping("addLevel")
    @ResponseBody
    public String addPiao(HttpSession session,Level level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(level);
        //日志记录
        logoService.save(new LogoObj("添加等级："+level.getMm_level_name(), manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_level_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_level_id);
        //日志记录
        logoService.save(new LogoObj("删除等级："+mm_level_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        Level level = (Level) levelServiceSaveExe.execute(typeId);
        map.put("levelObj", level);
        //日志记录
        logoService.save(new LogoObj("编辑等级："+level.getMm_level_name(), manager.getMm_manager_id()));
        return "/level/editlevel";
    }

    /**
     * 更新
     * @param level
     * @return
     */
    @RequestMapping("/editLevel")
    @ResponseBody
    public String updateGoodsType(HttpSession session,Level level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(level);
            //日志记录
            logoService.save(new LogoObj("编辑等级："+level.getMm_level_name(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
