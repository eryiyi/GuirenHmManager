package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.*;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvSell")
public class WebvSellController extends ControllerConstants {

    @Autowired
    @Qualifier("webVRecordService")
    private ListService recordService;

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;
    @Autowired
    @Qualifier("countryService")
    private ListService countryService;

    @Autowired
    @Qualifier("adObjService")
    private ListService adObjService;

    @RequestMapping("toSell")
    public String add(HttpSession session, ModelMap map, RecordQuery query, Page page){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);

        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        query.setMm_msg_type("1");

        try {
            Object[] results = (Object[]) recordService.list(query);
            map.put("list", results[0]);
            long count = (Long) results[1];
            page.setCount(count);
            page.setPageCount(calculatePageCount(query.getSize(), count));
            map.addAttribute("page", page);
            map.addAttribute("query", query);
            map.addAttribute("mm_msg_type", "1");
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return "/webv/login";
            }else{
                return "/webv/login";
            }
        }

        //查询省份
        ProvinceQuery provinceQuery = new ProvinceQuery();
        provinceQuery.setIs_use("1");
        List<ProvinceObj> listProvinces = (List<ProvinceObj>) provinceService.list(provinceQuery);
        //查询地市all
        CityQuery cityQueryAll = new CityQuery();
        cityQueryAll.setIs_use("1");
        List<CityObj> listCitysAll = (List<CityObj>) cityService.list(cityQueryAll);
        //查询县区all
        CountryQuery countryQueryAll = new CountryQuery();
        countryQueryAll.setIs_use("1");
        List<CountryObj> listsCountryAll = (List<CountryObj>) countryService.list(countryQueryAll);

        map.put("listProvinces", listProvinces);
        map.put("listCitysAll", toJSONString(listCitysAll));
        map.put("listsCountryAll", toJSONString(listsCountryAll));

        if(emp != null){
            //说明已经登陆
            map.put("is_login", "1");
            map.put("emp", emp);
        }else{
            //说明没有登陆
            map.put("is_login", "0");
        }
        //查询广告
        AdQuery queryad = new AdQuery();
        queryad.setMm_ad_type("2");
        List<AdObj> listAd = (List<AdObj>) adObjService.list(queryad);
        map.put("listAd", listAd);
        return "/webv/sell";
    }

}
