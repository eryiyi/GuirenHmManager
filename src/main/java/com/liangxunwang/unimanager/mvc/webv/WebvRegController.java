package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.*;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/webvRegController")
public class WebvRegController extends ControllerConstants {
    @Autowired
    @Qualifier("appProvinceService")
    private ListService appProvinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;
    @Autowired
    @Qualifier("countryService")
    private ListService countryService;

    @RequestMapping("toReg")
    public String toReg(ModelMap map){
        //查询省份
        ProvinceQuery provinceQuery = new ProvinceQuery();
        provinceQuery.setIs_use("1");
        List<ProvinceObj> listProvinces = (List<ProvinceObj>) appProvinceService.list(provinceQuery);
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

        return "/webv/reg";
    }

    @Autowired
    @Qualifier("webProfileService")
    private SaveService webProfileService;

    @RequestMapping("/empReg")
    @ResponseBody
    public String empReg(Emp member){
        try {
            webProfileService.save(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("MobileIsUse")){
                return toJSONString(ERROR_2);
            }else if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_1);
            }else{
                return toJSONString(ERROR_1);
            }
        }

    }


}
