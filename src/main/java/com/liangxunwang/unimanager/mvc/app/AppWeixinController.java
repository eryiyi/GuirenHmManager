package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.WeixinObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.KefuQuery;
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
 */
@Controller
public class AppWeixinController extends ControllerConstants {

    @Autowired
    @Qualifier("weixinService")
    private ListService levelService;

    //列表
    @RequestMapping(value = "/getKefuWeixin", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getKefuWeixin(KefuQuery query){
        try {
            List<WeixinObj> list = (List<WeixinObj>) levelService.list(query);
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
