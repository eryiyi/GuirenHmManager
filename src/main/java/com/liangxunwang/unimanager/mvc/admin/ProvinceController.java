package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
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
@RequestMapping("/province")
public class ProvinceController extends ControllerConstants {

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;

    @Autowired
    @Qualifier("provinceService")
    private ExecuteService provinceServiceExe;

    @Autowired
    @Qualifier("provinceService")
    private UpdateService provinceServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, ProvinceQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<ProvinceObj> list = (List<ProvinceObj>) provinceService.list(query);
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看省份列表", manager.getMm_manager_id()));
        return "/province/list";
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        ProvinceObj provinceObj = (ProvinceObj) provinceServiceExe.execute(id);
        map.put("provinceObj", provinceObj);
        //日志记录
        logoService.save(new LogoObj("编辑省份："+provinceObj.getPronum(), manager.getMm_manager_id()));
        return "/province/editprovince";
    }

    /**
     * 更新
     * @param provinceObj
     * @return
     */
    @RequestMapping("/editProvince")
    @ResponseBody
    public String updateGoodsType(HttpSession session, ProvinceObj provinceObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            provinceServiceUpdate.update(provinceObj);
            //日志记录
            logoService.save(new LogoObj("编辑省份："+provinceObj.getPronum(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
