package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.HangYeTypeVO;
import com.liangxunwang.unimanager.query.HangYeTypeQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
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
public class AppHangYeController extends ControllerConstants {

    @Autowired
    @Qualifier("hangYeTypeService")
    private ListService hangYeTypeService;

    //行业列表
    @RequestMapping(value = "/getHyTypes", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getHyTypes(HangYeTypeQuery query) throws Exception {
        try {
            List<HangYeTypeVO> lists = (List<HangYeTypeVO>) hangYeTypeService.list(query);
            DataTip tip = new DataTip();
            tip.setData(lists);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
