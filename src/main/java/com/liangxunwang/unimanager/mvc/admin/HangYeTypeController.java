package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.HangYeType;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.HangYeTypeVO;
import com.liangxunwang.unimanager.query.HangYeTypeQuery;
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

@Controller
@RequestMapping("/hangYeTypeController")
public class HangYeTypeController extends ControllerConstants {

    @Autowired
    @Qualifier("hangYeTypeService")
    private ListService levelService;

    @Autowired
    @Qualifier("hangYeTypeTopService")
    private ListService hangYeTypeTopService;

    @Autowired
    @Qualifier("hangYeTypeService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("hangYeTypeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("hangYeTypeService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("hangYeTypeService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, HangYeTypeQuery query){
        List<HangYeTypeVO> list = (List<HangYeTypeVO>) levelService.list(query);
        map.put("list", list);

        HangYeTypeQuery hangYeTypeQuery = new HangYeTypeQuery();
        List<HangYeTypeVO> listTop = (List<HangYeTypeVO>) hangYeTypeTopService.list(hangYeTypeQuery);
        map.put("listTop", listTop);
        return "/hangYeType/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(ModelMap map , HangYeTypeQuery hangYeTypeQuery){
        //查询行业列表
        List<HangYeTypeVO> list = (List<HangYeTypeVO>) hangYeTypeTopService.list(hangYeTypeQuery);
        map.put("list", list);
        return "/hangYeType/addHangYeType";
    }

    @RequestMapping("addHangYeType")
    @ResponseBody
    public String addHangYeType(HttpSession session,HangYeType hangYeType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(hangYeType);
        //日志记录
        logoService.save(new LogoObj("添加行业类别："+hangYeType.getMm_hangye_name(), manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_hangye_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_hangye_id);
        //日志记录
        logoService.save(new LogoObj("删除行业类别："+mm_hangye_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String edit(HttpSession session,ModelMap map, String mm_hangye_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        HangYeType hangYeType = (HangYeType) levelServiceSaveExe.execute(mm_hangye_id);
        map.put("hangYeType", hangYeType);
        HangYeTypeQuery query = new HangYeTypeQuery();
        List<HangYeTypeVO> list = (List<HangYeTypeVO>) hangYeTypeTopService.list(query);
        map.put("list", list);
        return "/hangYeType/editHangYeType";
    }

    /**
     * 更新
     * @param hangYeType
     * @return
     */
    @RequestMapping("/editHangYeType")
    @ResponseBody
    public String editHangYeType(HttpSession session,HangYeType hangYeType){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(hangYeType);
            //日志记录
            logoService.save(new LogoObj("编辑行业类别："+hangYeType.getMm_hangye_name(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
