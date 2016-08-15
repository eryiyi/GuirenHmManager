package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.MoneyJiageObj;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
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
 */
@Controller
@RequestMapping("/moneyJiageController")
public class MoneyJiageController extends ControllerConstants {

    @Autowired
    @Qualifier("moneyJiageObjObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("moneyJiageObjObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("moneyJiageObjObjService")
    private UpdateService levelServiceSaveUpdate;


    @RequestMapping("list")
    public String list(ModelMap map, AdQuery query){
        List<MoneyJiageObj> list = (List<MoneyJiageObj>) levelService.list(query);
        map.put("list", list);
        return "/moneyjiage/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(ModelMap map, String money_jiage_id) throws Exception {
        MoneyJiageObj moneyJiageObj = (MoneyJiageObj) levelServiceSaveExe.execute(money_jiage_id);
        map.put("moneyJiageObj", moneyJiageObj);
        return "/moneyjiage/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String updateGoodsType(MoneyJiageObj moneyJiageObj){
        try {
            levelServiceSaveUpdate.update(moneyJiageObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
