package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.mvc.vo.LogoVO;
import com.liangxunwang.unimanager.query.LogoQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.DeleteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
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
@RequestMapping("/logo")
public class LogoController extends ControllerConstants {

    @Autowired
    @Qualifier("logoService")
    private ListService logoService;

    @Autowired
    @Qualifier("logoService")
    private DeleteService logoServiceDel;

    @Autowired
    @Qualifier("adminAllService")
    private ListService adminAllService;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, LogoQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) logoService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //选择管理员
        List<AdminVO> listsManager = (List<AdminVO>) adminAllService.list("");
        map.put("listsManager", listsManager);
        return "logo/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_logo_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        logoServiceDel.delete(mm_logo_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("deleteAll")
    @ResponseBody
    public String deleteAll(HttpSession session,String ids){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            for(int i=0;i<arrs.length;i++){
                logoServiceDel.delete(arrs[i]);
            }
        }
        return toJSONString(SUCCESS);
    }


}
