package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
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
@RequestMapping("/webvRecommend")
public class WebvRecommendController extends ControllerConstants {

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;
    @Autowired
    @Qualifier("countryService")
    private ListService countryService;

    @RequestMapping("toRecommend")
    public String add(HttpSession session, ModelMap map, RecordQuery query, Page page){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);

        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());


        try {
            map.addAttribute("page", page);
            map.addAttribute("query", query);
            map.addAttribute("mm_msg_type", "0");

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
            return "/webv/recommend";
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return "/webv/login";
            }else{
                return "/webv/login";
            }
        }
//
    }

}
