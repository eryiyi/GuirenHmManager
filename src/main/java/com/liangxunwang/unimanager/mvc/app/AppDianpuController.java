package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpDianpu;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import com.liangxunwang.unimanager.mvc.vo.SchoolThreePtBdVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.query.SchoolThreePtBdQuery;
import com.liangxunwang.unimanager.service.*;
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
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppDianpuController extends ControllerConstants {


    @Autowired
    @Qualifier("appMemberService")
    private ListService appMemberServiceList;

    @Autowired
    @Qualifier("managerInfoService")
    private SaveService managerInfoSaveService;

    @Autowired
    @Qualifier("managerInfoService")
    private UpdateService managerInfoUpdateService;

    /**
     * 获得所有的店铺
     * @return
     */
    @RequestMapping(value = "/appGetDianPu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetDianPu(MemberQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

            List<EmpDianpu> list = (List<EmpDianpu>) appMemberServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping(value = "/saveDianpu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    private String saveDianpu(ManagerInfo info){
        if (StringUtil.isNullOrEmpty(info.getId())){
            managerInfoSaveService.save(info);
        }else {
            managerInfoUpdateService.update(info);
        }
        return toJSONString(SUCCESS);
    }


    @Autowired
    @Qualifier("adObjService")
    private ListService adObjService;
    /**
     * 获得用户广告
     * @return
     */
    @RequestMapping(value = "/appGetAds", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetAds(AdQuery query){
        try {
            List<AdObj> list = (List<AdObj>) adObjService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("schoolThreePingtaiBdService")
    private ListService schoolThreePingtaiBdService;
    /**
     * 获得第三方平台帮顶的
     * @return
     */
    @RequestMapping(value = "/appGetThreesBd", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetThreesBd(SchoolThreePtBdQuery query){
        try {
            List<SchoolThreePtBdVO> list = (List<SchoolThreePtBdVO>) schoolThreePingtaiBdService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("appManagerInfoService")
    private FindService appManagerInfoService;
    /**
     * 获得个人店铺信息
     * @return
     */
    @RequestMapping(value = "/appGetProfileMsg", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetProfileMsg(String emp_id){
        try {
            ManagerInfo managerInfoVo = (ManagerInfo) appManagerInfoService.findById(emp_id);
            DataTip tip = new DataTip();
            tip.setData(managerInfoVo);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
