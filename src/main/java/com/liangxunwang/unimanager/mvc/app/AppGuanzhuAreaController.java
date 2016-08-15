package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.GuanzhuAreaObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.GuanzhuAreaQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppGuanzhuAreaController extends ControllerConstants {

    @Autowired
    @Qualifier("guanzhuAreaService")
    private SaveService guanzhuAreaService;

    @Autowired
    @Qualifier("guanzhuAreaService")
    private ListService guanzhuAreaServiceLists;

    //保存关注区域申请
    @RequestMapping(value = "/saveGuanzhuArea", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getVipList(GuanzhuAreaObj guanzhuAreaObj){
        try {
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

    //查询关注区域
    @RequestMapping(value = "/getGuanzhuArea", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getGuanzhuArea(GuanzhuAreaQuery query){
        try {
            Object[] results = (Object[]) guanzhuAreaServiceLists.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
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
