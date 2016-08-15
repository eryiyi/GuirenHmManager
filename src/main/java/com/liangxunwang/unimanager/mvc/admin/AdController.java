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
@RequestMapping("/adObj")
public class AdController extends ControllerConstants {

    @Autowired
    @Qualifier("adObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("adObjService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("adObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("adObjService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("adObjService")
    private DeleteService levelServiceSaveDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;
    @Autowired
    @Qualifier("countryService")
    private ListService countryService;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<AdObj> list = (List<AdObj>) levelService.list(query);
        map.put("list", list);
        return "/ad/list";
    }

    @RequestMapping("add")
    public String add(ModelMap map, LevelQuery query){
        //查询省份
        ProvinceQuery provinceQuery = new ProvinceQuery();
        provinceQuery.setIs_use("1");
        List<ProvinceObj> listProvinces = (List<ProvinceObj>) provinceService.list(provinceQuery);
        //查询地市
        CityQuery cityQuery = new CityQuery();
        cityQuery.setIs_use("1");
        List<CityObj> listCitys = (List<CityObj>) cityService.list(cityQuery);
        //查询县区
        CountryQuery countryQuery = new CountryQuery();
        countryQuery.setIs_use("1");
        List<CountryObj> listsCountry = (List<CountryObj>) countryService.list(countryQuery);
        map.put("listProvinces", listProvinces);
        map.put("listCitys", listCitys);

        map.put("listsCountry", listsCountry);
        //查询地市all
        CityQuery cityQueryAll = new CityQuery();
        cityQueryAll.setIs_use("1");
        List<CityObj> listCitysAll = (List<CityObj>) cityService.list(cityQueryAll);
        //查询县区all
        CountryQuery countryQueryAll = new CountryQuery();
        countryQueryAll.setIs_use("1");
        List<CountryObj> listsCountryAll = (List<CountryObj>) countryService.list(countryQueryAll);

        map.put("listCitysAll", toJSONString(listCitysAll));
        map.put("listsCountryAll", toJSONString(listsCountryAll));
        return "/ad/addAd";
    }

    @RequestMapping("addAd")
    @ResponseBody
    public String addPiao(HttpSession session,AdObj adObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(adObj);
        //日志记录
        logoService.save(new LogoObj("添加广告："+adObj.getMm_ad_pic(), manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_ad_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_ad_id);
        //日志记录
        logoService.save(new LogoObj("删除广告："+mm_ad_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        AdObj adObj = (AdObj) levelServiceSaveExe.execute(typeId);
        map.put("adObj", adObj);
        //日志记录
        logoService.save(new LogoObj("编辑广告："+adObj.getMm_ad_id(), manager.getMm_manager_id()));
        return "/ad/editAd";
    }

    /**
     * 更新
     */
    @RequestMapping("/editAd")
    @ResponseBody
    public String updateGoodsType(HttpSession session,AdObj adObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(adObj);
            //日志记录
            logoService.save(new LogoObj("编辑广告："+adObj.getMm_ad_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
