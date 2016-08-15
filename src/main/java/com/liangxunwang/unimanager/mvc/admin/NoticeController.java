package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.CityQuery;
import com.liangxunwang.unimanager.query.CountryQuery;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.query.NoticeQuery;
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
@RequestMapping("noticeController")
public class NoticeController extends ControllerConstants {
    @Autowired
    @Qualifier("noticeService")
    private ListService noticeServiceList;

    @Autowired
    @Qualifier("noticeService")
    private SaveService noticeServiceSave;

    @Autowired
    @Qualifier("noticeService")
    private ExecuteService noticeServiceExe;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, NoticeQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) noticeServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //日志记录
        logoService.save(new LogoObj("查看公告列表", manager.getMm_manager_id()));
        return "/notice/list";
    }

    @RequestMapping("toAdd")
    public String add(){
        return "/notice/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(HttpSession session,Notice notice){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        notice.setMm_manager_id(manager.getMm_manager_id());
        noticeServiceSave.save(notice);
        //日志记录
        logoService.save(new LogoObj("添加公告", manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toDetail")
    public String toUpdateType(HttpSession session,ModelMap map, String id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        Notice notice = (Notice) noticeServiceExe.execute(id);
        //日志记录
        logoService.save(new LogoObj("查看公告详情："+notice.getMm_notice_title(), manager.getMm_manager_id()));
        map.put("notice", notice);
        return "/notice/detail";
    }


}
