package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.FeiyongVO;
import com.liangxunwang.unimanager.query.FeiyongQuery;
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
public class AppLevelController extends ControllerConstants {

    @Autowired
    @Qualifier("levelService")
    private ExecuteService levelServiceSave;

    //vip列表
    @RequestMapping(value = "/getLevelById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getVipList(String mm_level_id) throws Exception {
        try {
            Level level = (Level) levelServiceSave.execute(mm_level_id);
            DataTip tip = new DataTip();
            tip.setData(level);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
