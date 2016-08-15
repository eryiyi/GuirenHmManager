package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.FavourVO;
import com.liangxunwang.unimanager.query.FavourQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
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
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvMineFavourController")
public class WebvMineFavourController extends ControllerConstants {


    @Autowired
    @Qualifier("appFavourService")
    private ListService appFavourService;

    @RequestMapping("favour")
    public String favour(FavourQuery query,HttpSession session, ModelMap map){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(emp == null){
            return "/webv/login";
        }else
            map.put("is_login", "1");
            map.put("emp", emp);
        try {
            List<FavourVO> list = (List<FavourVO>) appFavourService.list(query);
            map.put("list", list);
            return "/webv/mineFavour";
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return "/webv/login";
            }else{
                return "/webv/login";
            }
        }


    }



}
