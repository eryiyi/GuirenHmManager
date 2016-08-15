package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.GuanzhuAreaObj;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.GuanzhuAreaObjVO;
import com.liangxunwang.unimanager.query.GuanzhuAreaQuery;
import com.liangxunwang.unimanager.query.LevelQuery;
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
@RequestMapping("/guanzhu")
public class GuanzhuObjController extends ControllerConstants {

    @Autowired
    @Qualifier("guanzhuAreaService")
    private ListService guanzhuAreaService;

    @Autowired
    @Qualifier("guanzhuAreaService")
    private ExecuteService guanzhuAreaServiceExe;

    @Autowired
    @Qualifier("guanzhuAreaService")
    private UpdateService guanzhuAreaServiceUpdate;

    @Autowired
    @Qualifier("guanzhuAreaService")
    private DeleteService guanzhuAreaServiceDel;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, GuanzhuAreaQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) guanzhuAreaService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);

        //日志记录
        logoService.save(new LogoObj("查看关注区域列表", manager.getMm_manager_id()));
        return "/guanzhu/list";
    }

    @RequestMapping("add")
    public String add(ModelMap map, LevelQuery query){
        return "/guanzhu/addlevel";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_guanzhu_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        guanzhuAreaServiceDel.delete(mm_guanzhu_id);
        //日志记录
        logoService.save(new LogoObj("删除关注区域："+mm_guanzhu_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String mm_guanzhu_id) throws Exception {
        GuanzhuAreaObjVO guanzhuAreaObjVO = (GuanzhuAreaObjVO) guanzhuAreaServiceExe.execute(mm_guanzhu_id);
        map.put("guanzhuAreaObjVO", guanzhuAreaObjVO);
        return "/guanzhu/detail";
    }

    /**
     * 更新
     * @param guanzhuAreaObj
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateGoodsType(HttpSession session,GuanzhuAreaObj guanzhuAreaObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            guanzhuAreaServiceUpdate.update(guanzhuAreaObj);
            //日志记录
            logoService.save(new LogoObj("审核关注区域："+guanzhuAreaObj.getMm_guanzhu_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
