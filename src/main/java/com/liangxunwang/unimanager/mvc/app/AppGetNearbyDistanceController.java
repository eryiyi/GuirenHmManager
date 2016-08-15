package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AboutUs;
import com.liangxunwang.unimanager.model.NearbyDistanceObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class AppGetNearbyDistanceController extends ControllerConstants {
    @Autowired
    @Qualifier("nearbyDistanceObjService")
    private ListService nearbyDistanceObjServiceList;

    //附近距离
    @RequestMapping(value = "/getNearbyDistance", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAboutUs(){
        try {
            List<NearbyDistanceObj> list = (List<NearbyDistanceObj>) nearbyDistanceObjServiceList.list("");
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
}
