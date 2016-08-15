package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.FeiyongObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.FeiyongVO;
import com.liangxunwang.unimanager.query.FeiyongQuery;
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
@RequestMapping("/feiyong")
public class FeiyongController extends ControllerConstants {

    @Autowired
    @Qualifier("feiyongService")
    private ListService feiyongServiceList;

    @Autowired
    @Qualifier("feiyongService")
    private SaveService feiyongServiceSave;

    @Autowired
    @Qualifier("feiyongService")
    private DeleteService feiyongServiceDelete;

    @Autowired
    @Qualifier("feiyongService")
    private ExecuteService feiyongServiceExe;


    @Autowired
    @Qualifier("feiyongService")
    private UpdateService feiyongServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session, ModelMap map, FeiyongQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<FeiyongVO> list = (List<FeiyongVO>) feiyongServiceList.list(query);
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看会费列表", manager.getMm_manager_id()));
        return "/feiyong/list";
    }


    @Autowired
    @Qualifier("levelService")
    private ListService levelService;

    @RequestMapping("add")
    public String add(ModelMap map, FeiyongQuery query){
        LevelQuery query1 = new LevelQuery();
        List<Level> list = (List<Level>) levelService.list(query1);
        map.put("listLevel", list);
        return "/feiyong/addfeiyong";
    }

    @RequestMapping("addFeiyong")
    @ResponseBody
    public String addPiao(HttpSession session,FeiyongObj feiyongObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        feiyongServiceSave.save(feiyongObj);
        //日志记录
        logoService.save(new LogoObj("添加费用："+feiyongObj.getMm_feiyong_name(), manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }


    @RequestMapping("delete")
    @ResponseBody
    public String delete(String mm_feiyong_id){
        feiyongServiceDelete.delete(mm_feiyong_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        FeiyongObj feiyong = (FeiyongObj) feiyongServiceExe.execute(typeId);
        map.put("feiyong", feiyong);
        LevelQuery query1 = new LevelQuery();
        List<Level> list = (List<Level>) levelService.list(query1);
        map.put("listLevel", list);

        //日志记录
        logoService.save(new LogoObj("查看费用详情："+feiyong.getMm_feiyong_name(), manager.getMm_manager_id()));
        return "/feiyong/editfeiyong";
    }

    /**
     * 更新
     * @param feiyongObj
     * @return
     */
    @RequestMapping("/editFeiyong")
    @ResponseBody
    public String updateGoodsType(HttpSession session,FeiyongObj feiyongObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            feiyongServiceUpdate.update(feiyongObj);
            //日志记录
            logoService.save(new LogoObj("编辑更新费用："+feiyongObj.getMm_feiyong_name(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
