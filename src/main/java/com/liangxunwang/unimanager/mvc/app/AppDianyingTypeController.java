package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.VideoTypeObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.AdQuery;
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
public class AppDianyingTypeController extends ControllerConstants {
    @Autowired
    @Qualifier("dianyingTypeObjService")
    private ListService videoTypeObjService;

    //列表电影类别
    @RequestMapping(value = "/getDianyingTypes", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getDianyingTypes(AdQuery query){
        try {
            List<VideoTypeObj> list = (List<VideoTypeObj>) videoTypeObjService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }

}
