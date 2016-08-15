package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.mvc.vo.EmpAdVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.EmpAdQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvServiceController")
public class WebvServiceController extends ControllerConstants {


    @Autowired
    @Qualifier("empAdService")
    private ListService empAdService;

    @RequestMapping("toService")
    public String add(HttpSession session, ModelMap map){
        EmpVO empVO = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(empVO != null) {
            //说明已经登陆
            map.put("emp", empVO);
            //获得用户的轮播图
            EmpAdQuery query = new EmpAdQuery();
            query.setMm_emp_id(empVO.getMm_emp_id());
            List<EmpAdVO> list = (List<EmpAdVO>) empAdService.list(query);
            if(list != null && list.size() == 0){
                //说明没有广告图，添加一个默认的
                EmpAdVO adVO = new EmpAdVO();
                adVO.setMm_emp_ad_pic(Constants.URL + Constants.DEFAULT_SERVICE_TOP_BG);
                adVO.setMm_emp_ad_url(Constants.URL + Constants.DEFAULT_DOWNLOAD_URL);
                list.add(adVO);
            }
            map.put("empAdVO", list);
            return "/webv/service";
        }else {
            return "/webv/login";
        }

    }


}
