package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.mvc.vo.CityVO;
import com.liangxunwang.unimanager.query.CityQuery;
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
@RequestMapping("/city")
public class CityController extends ControllerConstants {
    @Autowired
    @Qualifier("cityVOService")
    private ListService cityVoService;

    @Autowired
    @Qualifier("cityVOService")
    private ExecuteService cityVoServiceExe;


    @Autowired
    @Qualifier("cityVOService")
    private UpdateService cityVoServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, CityQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<CityVO> list = (List<CityVO>) cityVoService.list(query);
        map.put("list", list);

        ProvinceQuery provinceQuery = new ProvinceQuery();
        provinceQuery.setIs_use("1");
        //查询省份
        List<ProvinceObj> listProvinces = (List<ProvinceObj>) provinceService.list(provinceQuery);
        map.put("listProvinces", listProvinces);

        //日志记录
        logoService.save(new LogoObj("查看城市列表", manager.getMm_manager_id()));
        return "/city/list";
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CityObj provinceObj = (CityObj) cityVoServiceExe.execute(id);
        map.put("provinceObj", provinceObj);
        //日志记录
        logoService.save(new LogoObj("编辑城市："+provinceObj.getCity(), manager.getMm_manager_id()));
        return "/city/editcity";
    }

    /**
     * 更新
     * @param cityObj
     * @return
     */
    @RequestMapping("/editCity")
    @ResponseBody
    public String editCity(HttpSession session, CityObj cityObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            cityVoServiceUpdate.update(cityObj);
            //日志记录
            logoService.save(new LogoObj("编辑城市："+cityObj.getCity(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
