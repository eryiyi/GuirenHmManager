package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.FuwuQuery;
import com.liangxunwang.unimanager.query.ProvinceQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppProvinceController extends ControllerConstants {

    @Autowired
    @Qualifier("appProvinceService")
    private ListService appProvinceService;

    //省份列表
    @RequestMapping(value = "/getProvince", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getProvince(ProvinceQuery query){
        try {
            query.setIs_use("1");
            List<ProvinceObj> list = (List<ProvinceObj>) appProvinceService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
