package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.EmpRelateObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpRelateObjVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.EmpRelateQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
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
public class AppEmpRelateController extends ControllerConstants {
    @Autowired
    @Qualifier("appEmpRelateService")
    private ListService appEmpRelateServiceLst;

    @Autowired
    @Qualifier("appEmpRelateService")
    private SaveService appEmpRelateServiceSave;

    @Autowired
    @Qualifier("appEmpRelateService")
    private UpdateService appEmpRelateServiceUpdate;


    @Autowired
    @Qualifier("appEmpRelateService")
    private DeleteService appEmpRelateServiceDelete;


    //获得用户列表
    @RequestMapping(value = "/getEmpRelates", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getEmpRelates(EmpRelateQuery query){
        List<EmpRelateObjVO> list = (List<EmpRelateObjVO>) appEmpRelateServiceLst.list(query);
        DataTip tip = new DataTip();
        tip.setData(list);
        return toJSONString(tip);
    }


    //保存关系
    @RequestMapping(value = "/saveEmpRelate", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String saveEmpRelate(EmpRelateObj empRelateObj){
        try {
            appEmpRelateServiceSave.save(empRelateObj);
            DataTip tip = new DataTip();
            tip.setData(tip);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("has_exist")){
                return toJSONString(ERROR_2);//已经存在关系了  不需要重复保存
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }

    //更新关系
    @RequestMapping(value = "/updateEmpRelate", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateEmpRelate(EmpRelateObj empRelateObj){
        if("1".equals(empRelateObj.getState())){
            //同意
            appEmpRelateServiceUpdate.update(empRelateObj);
        }else if("2".equals(empRelateObj.getState())){
            //拒绝
            appEmpRelateServiceDelete.delete(empRelateObj);
        }
        DataTip tip = new DataTip();
        tip.setData(tip);
        return toJSONString(tip);
    }

}
