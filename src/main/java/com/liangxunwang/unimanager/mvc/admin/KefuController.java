package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.query.KefuQuery;
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

@Controller
@RequestMapping("/kefu")
public class KefuController extends ControllerConstants {
    @Autowired
    @Qualifier("kefuTelService")
    private ListService levelService;

    @Autowired
    @Qualifier("kefuTelService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("kefuTelService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("kefuTelService")
    private ExecuteService levelServiceSaveExe;

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
    public String list(HttpSession session,ModelMap map, KefuQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<KefuVO> list = (List<KefuVO>) levelService.list(query);
        map.put("list", list);
        //日志记录
        logoService.save(new LogoObj("查看客服", manager.getMm_manager_id()));
        return "/kefu/list";
    }

    @RequestMapping("add")
    public String add(ModelMap map, KefuQuery query){
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

        return "/kefu/addkefu";
    }

    @RequestMapping("addKefu")
    @ResponseBody
    public String addKefu(HttpSession session,KefuTel level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(level);
        //日志记录
        logoService.save(new LogoObj("添加客服", manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }


    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        KefuVO level = (KefuVO) levelServiceSaveExe.execute(typeId);
        map.put("levelObj", level);
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

        //日志记录
        logoService.save(new LogoObj("编辑客服："+level.getMm_tel_id(), manager.getMm_manager_id()));
        return "/kefu/editkefu";
    }

    /**
     * 更新
     * @param kefuTel
     * @return
     */
    @RequestMapping("/editKefu")
    @ResponseBody
    public String editKefu(HttpSession session,KefuTel kefuTel){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(kefuTel);
            //日志记录
            logoService.save(new LogoObj("编辑客服："+kefuTel.getMm_tel_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
