package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.query.*;
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
 */
@Controller
@RequestMapping("/goodTypeObjController")
public class GoodTypeObjController extends ControllerConstants {

    @Autowired
    @Qualifier("gdTypeObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("gdTypeObjService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("gdTypeObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("gdTypeObjService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("gdTypeObjService")
    private DeleteService levelServiceSaveDel;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query){
        List<GdTypeObj> list = (List<GdTypeObj>) levelService.list(query);
        map.put("list", list);
        return "/gdtype/list";
    }

    @RequestMapping("toAdd")
    public String add(){
        return "/gdtype/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(GdTypeObj gdTypeObj){
        levelServiceSave.save(gdTypeObj);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String gd_type_id){
        levelServiceSaveDel.delete(gd_type_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(ModelMap map, String typeId) throws Exception {
        GdTypeObj gdTypeObj = (GdTypeObj) levelServiceSaveExe.execute(typeId);
        map.put("gdTypeObj", gdTypeObj);
        return "/gdtype/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(GdTypeObj adObj){
        try {
            levelServiceSaveUpdate.update(adObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
