package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.query.KefuQuery;
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
@RequestMapping("/weixin")
public class WeixinController extends ControllerConstants {
    @Autowired
    @Qualifier("weixinService")
    private ListService levelService;

    @Autowired
    @Qualifier("weixinService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("weixinService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("weixinService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("weixinService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, KefuQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<WeixinObj> list = (List<WeixinObj>) levelService.list(query);
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看微信客服", manager.getMm_manager_id()));
        return "/weixin/list";
    }

    @RequestMapping("add")
    public String add(ModelMap map){

        return "/weixin/addkefu";
    }

    @RequestMapping("addKefu")
    @ResponseBody
    public String addKefu(HttpSession session,WeixinObj level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(level);
        //日志记录
        logoService.save(new LogoObj("添加微信客服", manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }


    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        WeixinObj weixinObj = (WeixinObj) levelServiceSaveExe.execute(typeId);
        map.put("weixinObj", weixinObj);

        //日志记录
        logoService.save(new LogoObj("编辑微信客服："+weixinObj.getMm_weixin_id(), manager.getMm_manager_id()));
        return "/weixin/editkefu";
    }

    /**
     * 更新
     * @param weixinObj
     * @return
     */
    @RequestMapping("/editKefu")
    @ResponseBody
    public String editKefu(HttpSession session,WeixinObj weixinObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(weixinObj);
            //日志记录
            logoService.save(new LogoObj("编辑微信客服："+weixinObj.getMm_weixin_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_weixin_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_weixin_id);
        //日志记录
        logoService.save(new LogoObj("删除客服："+mm_weixin_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

}
