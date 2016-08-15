package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.GuanzhuAreaObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.mvc.vo.CityVO;
import com.liangxunwang.unimanager.mvc.vo.CountryVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.GuanzhuAreaObjVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.query.GuanzhuAreaQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.Null;
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
@RequestMapping("/webvSelectProvinceController")
public class WebvSelectProvinceController extends ControllerConstants {
    @Autowired
    @Qualifier("appProvinceService")
    private ListService appProvinceService;

    @Autowired
    @Qualifier("guanzhuAreaService")
    private ListService guanzhuAreaServiceLists;

    @RequestMapping("toSelectProvince")
    public String toSelectProvince(HttpSession session,ModelMap map){
         String[] areaNames = new String[10];
         String[] areaIds = new String[10];

        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp != null){
            ProvinceQuery provinceQuery = new ProvinceQuery();
            provinceQuery.setIs_use("1");
            List<ProvinceObj> list = (List<ProvinceObj>) appProvinceService.list(provinceQuery);
            map.put("list", list);

            GuanzhuAreaQuery query = new GuanzhuAreaQuery();
            query.setIndex(1);
            query.setSize(10);
            query.setMm_emp_id(emp.getMm_emp_id());
            Object[] results = (Object[]) guanzhuAreaServiceLists.list(query);
            List<GuanzhuAreaObjVO> listgz = (List<GuanzhuAreaObjVO>) results[0];
            GuanzhuAreaObj guanzhuAreaObj;
            if(listgz != null && listgz.size()>0){
                guanzhuAreaObj = listgz.get(0);
                if(guanzhuAreaObj != null){
                    if("0".equals(guanzhuAreaObj.getIscheck())){
//                                                    showMsg(SelectProvinceActivity.this, "您已经申请了关注区域！请等待管理员审核");
                        map.put("is_guanzhu", "0");
                    }else
                    if("1".equals(guanzhuAreaObj.getIscheck())){
//                                                    Intent intent = new Intent(SelectProvinceActivity.this, RecordGzActivity.class);
//                                                    intent.putExtra("guanzhuAreaObj", guanzhuAreaObj);
//                                                    startActivity(intent);
                        areaNames = guanzhuAreaObj.getArea_name().split(",");
                        areaIds = guanzhuAreaObj.getAreaid().split(",");
                        map.put("is_guanzhu", "1");
                        map.put("areaNames", areaNames);
                        map.put("areaIds", areaIds);
                    }else
                    if("2".equals(guanzhuAreaObj.getIscheck())){
//                                                    showMsg(SelectProvinceActivity.this, "您申请的关注区域未通过审核，请联系客服！");
                        map.put("is_guanzhu", "2");
                    }else{
//                                                    showMsg(SelectProvinceActivity.this, "您尚未申请关注区域，请设置关注区域！");
                        map.put("is_guanzhu", "3");
                    }
                }
            }else{
                map.put("is_guanzhu", "3");
            }

            return "/webv/select_province";
        }else {
            return "/webv/login";
        }
    }


    @Autowired
    @Qualifier("cityVOService")
    private ListService cityVoService;

    @RequestMapping("toSelectCity")
    public String toSelectCity(HttpSession session,ModelMap map,CityQuery query){
        query.setIs_use("1");
        List<CityVO> list = (List<CityVO>) cityVoService.list(query);
        map.put("list", list);
        return "/webv/select_city";
    }


    @Autowired
    @Qualifier("countryVOService")
    private ListService countryVOService;

    @RequestMapping("toSelectArea")
    public String toSelectArea(HttpSession session,ModelMap map,CountryQuery query){
        query.setIs_use("1");
        List<CountryVO> list = (List<CountryVO>) countryVOService.list(query);
        map.put("list", list);
        return "/webv/select_area";
    }

}
