package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.HelpObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.HelpObjQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
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
public class AppHelpObjController extends ControllerConstants {
    @Autowired
    @Qualifier("appHelpObjService")
    private SaveService appHelpObjServiceSave;


    @Autowired
    @Qualifier("appHelpObjService")
    private ListService appHelpObjServiceList;

    //列表
    @RequestMapping(value = "/appGetHelps", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getLoginAd(HelpObjQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            List<HelpObj> list = (List<HelpObj>) appHelpObjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    //保存
    @RequestMapping(value = "/appSaveHelp", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveHelp(HelpObj helpObj){
        if(StringUtil.isNullOrEmpty(helpObj.getMm_emp_id())){
            return toJSONString(ERROR_2);//用户ID为空
        }
        if(StringUtil.isNullOrEmpty(helpObj.getHelp_title())){
            return toJSONString(ERROR_3);//标题为空
        }
        if(StringUtil.isNullOrEmpty(helpObj.getHelp_content())){
            return toJSONString(ERROR_4);//内容为空
        }
        if(StringUtil.isNullOrEmpty(helpObj.getHelp_type_id())){
            return toJSONString(ERROR_5);//类型ID为空
        }
        try {
            appHelpObjServiceSave.save(helpObj);
            DataTip tip = new DataTip();
            tip.setData(SUCCESS);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
