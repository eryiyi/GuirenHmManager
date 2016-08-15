package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.YaoqingCardVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
import com.liangxunwang.unimanager.query.YaoqingCardQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/yaoqingCardController")
public class YaoqingCardController extends ControllerConstants {

    @Autowired
    @Qualifier("yaoqingCardService")
    private ListService levelService;

    @Autowired
    @Qualifier("yaoqingCardService")
    private SaveService yaoqingCardServiceSave;

    @Autowired
    @Qualifier("yaoqingCardService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("yaoqingCardService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, YaoqingCardQuery query,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) levelService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);

        return "/yaoqingcard/list";
    }

    @RequestMapping("toAdd")
    public String add(ModelMap map, YaoqingCardQuery query){
        return "/yaoqingcard/addYaoqingCard";
    }

    @RequestMapping("addYaoqingCard")
    @ResponseBody
    public String addYaoqingCard(HttpSession session,YaoqingCardQuery yaoqingCardQuery){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        //根据要生成多少个

        yaoqingCardServiceSave.save(yaoqingCardQuery);
        //日志记录
        logoService.save(new LogoObj("添加邀请码："+ yaoqingCardQuery.getAdd_one(), manager.getMm_manager_id()));

        return toJSONString(SUCCESS);
    }


    @RequestMapping("/edit")
    public String toEdit(HttpSession session,ModelMap map, String id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        YaoqingCard yaoqingCard = (YaoqingCard) levelServiceSaveExe.execute(id);
        map.put("yaoqingCard", yaoqingCard);
        return "/yaoqingcard/editYqCard";
    }

    /**
     * 更新
     * @param yaoqingCard
     * @return
     */
    @RequestMapping("/editYqCard")
    @ResponseBody
    public String editYqCard(HttpSession session, YaoqingCard yaoqingCard){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(yaoqingCard);
            //日志记录
            logoService.save(new LogoObj("更新邀请码："+yaoqingCard.getGuiren_card_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
