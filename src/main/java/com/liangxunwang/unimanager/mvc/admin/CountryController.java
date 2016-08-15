package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.CityVO;
import com.liangxunwang.unimanager.mvc.vo.CountryVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.apache.ibatis.annotations.Update;
import org.apache.poi.ss.formula.functions.Count;
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
@RequestMapping("/country")
public class CountryController extends ControllerConstants {

    @Autowired
    @Qualifier("countryVOService")
    private ListService countryVOService;

    @Autowired
    @Qualifier("countryVOService")
    private ExecuteService countryVOServiceExe;

    @Autowired
    @Qualifier("countryVOService")
    private UpdateService countryVOServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;

    @RequestMapping("list")
    public String list(HttpSession session, ModelMap map, CountryQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<CountryVO> list = (List<CountryVO>) countryVOService.list(query);
        map.put("list", list);

        //查询地市
        CityQuery cityQuery = new CityQuery();
        cityQuery.setIs_use("1");
        List<CityObj> listCitys = (List<CityObj>) cityService.list(cityQuery);
        map.put("listCitys", listCitys);
        //日志记录
        logoService.save(new LogoObj("查看区县列表", manager.getMm_manager_id()));
        return "/country/list";
    }


    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CountryObj provinceObj = (CountryObj) countryVOServiceExe.execute(id);
        map.put("provinceObj", provinceObj);
        //日志记录
        logoService.save(new LogoObj("编辑区县："+provinceObj.getArea(), manager.getMm_manager_id()));
        return "/country/editcountry";
    }

    /**
     * 更新
     * @param countryObj
     * @return
     */
    @RequestMapping("/editCountry")
    @ResponseBody
    public String editCountry(HttpSession session, CountryObj countryObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            countryVOServiceUpdate.update(countryObj);
            //日志记录
            logoService.save(new LogoObj("编辑县区："+countryObj.getArea(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


}
