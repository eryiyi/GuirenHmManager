package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppXixunController extends ControllerConstants {

    @Autowired
    @Qualifier("appXixunService")
    private ListService appXixunServiceList;


    @Autowired
    @Qualifier("appXixunService")
    private SaveService appXixunServiceSave;


    //
    @RequestMapping(value = "/xixunList", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRecord(RecordQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            Object[] results = (Object[]) appXixunServiceList.list(query);
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

//
//    @RequestMapping(value = "/saveXixun", produces = "text/plain;charset=UTF-8;")
//    @ResponseBody
//    public String save(Record record, HttpSession session){
//        try {
//            RecordVO recordAdd = (RecordVO) appXixunServiceSave.save(record);//保存信息
//            DataTip tip = new DataTip();
//            tip.setData(recordAdd);
//            return toJSONString(tip);
//        }catch (ServiceException e){
//            if (e.getMessage().equals("HAS_PUBLISH")){
//                return toJSONString(ERROR_2);
//            }else if(e.getMessage().equals("HAS_FULL")){
//                return toJSONString(ERROR_3);
//            }else if(e.getMessage().equals("accessTokenNull")){
//                return toJSONString(ERROR_9);
//            }
//            else{
//                return toJSONString(ERROR_1);
//            }
//        }
//    }

}
