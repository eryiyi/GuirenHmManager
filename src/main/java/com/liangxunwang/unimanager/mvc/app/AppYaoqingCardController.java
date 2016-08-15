package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.model.YaoqingCard;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.FeiyongVO;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.mvc.vo.YaoqingCardVO;
import com.liangxunwang.unimanager.query.FeiyongQuery;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.query.YaoqingCardQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppYaoqingCardController extends ControllerConstants {

    @Autowired
    @Qualifier("appYaoqingCardService")
    private ListService appYaoqingCardServiceList;

    @Autowired
    @Qualifier("appYaoqingCardService")
    private ExecuteService appYaoqingCardServiceExe;

    @RequestMapping(value = "/getYaoqingCardEmp", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getYaoqingCardEmp( YaoqingCardQuery query){
        try {
            List<YaoqingCardVO> list = (List<YaoqingCardVO>) appYaoqingCardServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }




}
