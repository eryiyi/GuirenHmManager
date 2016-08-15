package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RelateVO;
import com.liangxunwang.unimanager.query.RelateQuery;
import com.liangxunwang.unimanager.query.RenmaiQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/2/8.
 */
@Controller
public class TuopuController extends ControllerConstants {

    @Autowired
    @Qualifier("appEmpRenmaiService")
    private ListService appEmpRenmaiService;

    //根据两会员人脉度 查询他俩的拓扑关系

    @RequestMapping(value = "/listRenmaidu", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String listRenmaidu(RenmaiQuery query){
        try {
            Object[] results = (Object[]) appEmpRenmaiService.list(query);
//            List<EmpVO> relates = (List<EmpVO>) results [0];
//            List<EmpVO> relates1 = (List<EmpVO>) results [1];
            DataTip dataTip = new DataTip();
            dataTip.setData(results);
            return toJSONString(dataTip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
