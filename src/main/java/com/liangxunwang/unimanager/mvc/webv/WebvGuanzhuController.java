package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.GuanzhuAreaObjVO;
import com.liangxunwang.unimanager.query.*;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/webvGuanzhuController")
public class WebvGuanzhuController extends ControllerConstants {
    @Autowired
    @Qualifier("appProvinceService")
    private ListService appProvinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;

    @Autowired
    @Qualifier("countryService")
    private ListService countryService;

    @Autowired
    @Qualifier("guanzhuAreaService")
    private ListService guanzhuAreaServiceLists;

    //查询关注区域
    @RequestMapping(value = "/webvGetGuanzhu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getGuanzhuArea(GuanzhuAreaQuery query){
        try {
            Object[] results = (Object[]) guanzhuAreaServiceLists.list(query);
            List<GuanzhuAreaObjVO> lists = (List<GuanzhuAreaObjVO>) results[0];
            if(lists != null && lists.size()>0){
                GuanzhuAreaObj guanzhuAreaObj = lists.get(0);
                if(guanzhuAreaObj != null){
                    if("0".equals(guanzhuAreaObj.getIscheck())){
//                        showMsg(MainActivity.this, "您已经申请了关注区域！请等待管理员审核");
                        return toJSONString(ERROR_3);
                    }else if("1".equals(guanzhuAreaObj.getIscheck())){
                       //
                        return toJSONString(SUCCESS);
                    }else if("2".equals(guanzhuAreaObj.getIscheck())){
//                        showMsg(MainActivity.this, "您申请的关注区域未通过审核，请联系客服！");
                        return toJSONString(ERROR_4);
                    }else{
//                        showMsg(MainActivity.this, "您尚未申请关注区域，请设置关注区域！");
                        return toJSONString(ERROR_2);
                    }
                }
            }else {
                //您尚未申请关注区域，请设置关注区域！
                return toJSONString(ERROR_2);
            }
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(ERROR_1);
    }

    @RequestMapping("toGuanzhu")
    public String toReg(HttpSession session,ModelMap map){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp != null){
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

            return "/webv/addGuanzhu";
        }else {
            return "/webv/login";
        }

    }

    @Autowired
    @Qualifier("webVRecordService")
    private ListService recordService;

    @RequestMapping("/guanzhuArea")
    public String add(HttpSession session, ModelMap map, RecordQuery query, Page page) {
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(StringUtil.isNullOrEmpty(query.getMm_msg_type())){
            query.setMm_msg_type("0");
        }
        if(emp != null){
            GuanzhuAreaQuery guanzhuAreaQuery = new GuanzhuAreaQuery();
            guanzhuAreaQuery.setMm_emp_id(emp.getMm_emp_id());
            guanzhuAreaQuery.setIndex(1);
            guanzhuAreaQuery.setSize(10);
            guanzhuAreaQuery.setIscheck("1");
            Object[] resultsArea = (Object[]) guanzhuAreaServiceLists.list(guanzhuAreaQuery);
            List<GuanzhuAreaObjVO> lists = (List<GuanzhuAreaObjVO>) resultsArea[0];
            GuanzhuAreaObj guanzhuAreaObj=null;
            if(lists != null && lists.size()>0){
                guanzhuAreaObj = lists.get(0);
                if(guanzhuAreaObj != null){
                    String[] arrAreaid = guanzhuAreaObj.getAreaid().split(",");
                    String[] arrAreaName = guanzhuAreaObj.getArea_name().split(",");

                    List<CountryObj> listArea = new ArrayList<CountryObj>();
                    for(int i=0;i<arrAreaid.length;i++){
                        listArea.add(new CountryObj(arrAreaid[i], arrAreaName[i]));
                    }
                    map.put("countryid", arrAreaid[0]);


                    map.put("listArea", listArea);
                }
            }
            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());


            try {
                Object[] results = (Object[]) recordService.list(query);
                map.put("list", results[0]);
                long count = (Long) results[1];
                page.setCount(count);
                page.setPageCount(calculatePageCount(query.getSize(), count));
                map.addAttribute("page", page);
                map.addAttribute("query", query);
                map.addAttribute("mm_msg_type", "0");
            }catch (ServiceException e){
                String msg = e.getMessage();
                if (msg.equals("accessTokenNull")){
                    return "/webv/login";
                }else{
                    return "/webv/login";
                }
            }
            //说明已经登陆
            map.put("is_login", "1");
            map.put("emp", emp);
        }else{
            //说明没有登陆
            map.put("is_login", "0");
        }
        return "/webv/guanzhu_index";
    }


    @Autowired
    @Qualifier("guanzhuAreaService")
    private SaveService guanzhuAreaService;

    //保存关注区域申请
    @RequestMapping(value = "/saveGuanzhuArea", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getVipList(HttpSession session,GuanzhuAreaObj guanzhuAreaObj){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        try {
            if(emp != null){
                guanzhuAreaObj.setMm_emp_id(emp.getMm_emp_id());
            }
            guanzhuAreaService.save(guanzhuAreaObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else if(msg.equals("has_exist")){
                //已经存在了
                return toJSONString(ERROR_2);
            }
            else{
                return toJSONString(ERROR_1);
            }
        }
    }



}
