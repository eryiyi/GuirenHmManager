package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Favour;
import com.liangxunwang.unimanager.model.FuwuObj;
import com.liangxunwang.unimanager.model.ReportObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.FavourVO;
import com.liangxunwang.unimanager.query.FavourQuery;
import com.liangxunwang.unimanager.query.FuwuQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
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
public class AppFavourController extends ControllerConstants {

    @Autowired
    @Qualifier("appFavourService")
    private ListService appFavourService;

    @Autowired
    @Qualifier("appFavourService")
    private SaveService appFavourServiceSave;

    @Autowired
    @Qualifier("appFavourCountService")
    private ExecuteService appFavourCountService;

    @Autowired
    @Qualifier("appFavourDelService")
    private ExecuteService appFavourDelService;

    //收藏列表
    @RequestMapping(value = "/getFavourById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getFavourById(FavourQuery query){
        try {
            List<FavourVO> list = (List<FavourVO>) appFavourService.list(query);
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


    //保存收藏
    @RequestMapping(value = "/saveFavour", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String saveFavour(Favour favour){
        try {
            appFavourServiceSave.save(favour);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else if(msg.equals("hasExist")){
                return toJSONString(ERROR_2);
            }
            else{
                return toJSONString(ERROR_1);
            }
        }
    }


    //查询收藏数量
    @RequestMapping(value = "/getFavourCount", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getFavourCount(String mm_emp_id) throws Exception {
        try {
            String count = (String) appFavourCountService.execute(mm_emp_id);
            DataTip tip = new DataTip();
            tip.setData(count);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else if(msg.equals("hasExist")){
                return toJSONString(ERROR_2);
            }
            else{
                return toJSONString(ERROR_1);
            }
        }
    }


    //删除收藏
    @RequestMapping(value = "/deleteFavour", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String deleteFavour(String mm_record_favour_id) throws Exception {
        try {
            appFavourDelService.execute(mm_record_favour_id);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else if(msg.equals("hasExist")){
                return toJSONString(ERROR_2);
            }
            else{
                return toJSONString(ERROR_1);
            }
        }
    }
}
