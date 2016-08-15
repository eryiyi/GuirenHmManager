package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.CityVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class MemberRegisterController extends ControllerConstants {
    @Autowired
    @Qualifier("memberRegisterService")
    private SaveService memberRegisterService;

    @Autowired
    @Qualifier("memberRegisterService")
    private UpdateService memberUpdateService;

    @Autowired
    @Qualifier("provinceService")
    private ListService provinceService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;

    @Autowired
    @Qualifier("countryService")
    private ListService countryService;


    /**
     * 注册功能
     * @param member  会员对象
     * @return
     */
    @RequestMapping("/memberRegister")
    @ResponseBody
    public String register(Emp member){
        try {
            memberRegisterService.save(member);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("MobileIsUse")){
                return toJSONString(ERROR_2);
            }
            if (msg.equals("CardIsError")){
                return toJSONString(ERROR_3);
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_1);
            }

        }
        return toJSONString(SUCCESS);
    }

    /**
     * 完善个人资料
     * @param member  会员对象
     * @return
     */
    @RequestMapping("/memberUpdateProfile")
    @ResponseBody
    public String updateProfile(Emp member){
        try {
            memberUpdateService.update(member);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals(Constants.SAVE_ERROR)){
                //更新失败
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }


//    /**
//     * 获得所有的省份
//     * @return
//     */
//    @RequestMapping(value = "/getProvince", produces = "text/plain;charset=UTF-8")
//    @ResponseBody
//    public String getProvince(){
//        try {
//            List<ProvinceObj> list = (List<ProvinceObj>) provinceService.list(null);
//            DataTip tip = new DataTip();
//            tip.setData(list);
//            return toJSONString(tip);
//        }catch (ServiceException e){
//            return toJSONString(ERROR_1);
//        }
//    }

    /**
     * 根据省份ID获得该省份的城市
     * @param query
     * @return
     */
    @RequestMapping(value = "/getCity", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getCity(CityQuery query){
        try {
            List<CityObj> list = (List<CityObj>) cityService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    /**
     * 根据城市ID获得地区列表
     * @param query
     * @return
     */
    @RequestMapping(value = "/getCountry", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getCountry(CountryQuery query){
        try {
            List<CountryObj> list = (List<CountryObj>) countryService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
