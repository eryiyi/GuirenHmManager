package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.CityObj;
import com.liangxunwang.unimanager.model.CountryObj;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.model.ProvinceObj;
import com.liangxunwang.unimanager.mvc.vo.EmpAdVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.*;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
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
@RequestMapping("/webvNoticeController")
public class WebvNoticeController extends ControllerConstants {

    @Autowired
    @Qualifier("noticeService")
    private ListService noticeServiceList;

    @Autowired
    @Qualifier("noticeService")
    private ExecuteService noticeServiceExe;

    @RequestMapping("list")
    public String list(HttpSession session, ModelMap map, NoticeQuery query, Page page){
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            Object[] results = (Object[]) noticeServiceList.list(query);
            map.put("list", results[0]);
            long count = (Long) results[1];
            page.setCount(count);
            page.setPageCount(calculatePageCount(query.getSize(), count));
            map.addAttribute("page", page);
            map.addAttribute("query", query);
            if(emp != null){
                //说明已经登陆
                map.put("is_login", "1");
                map.put("emp", emp);
            }else{
                //说明没有登陆
                map.put("is_login", "0");
            }
            return "/webv/notice";
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return "/webv/login";
            }else{
                return "/webv/login";
            }
        }
    }

//    toDetail

    @RequestMapping("toDetail")
    public String toDetail(HttpSession session, ModelMap map,String id) throws Exception {
        EmpVO empVO = (EmpVO) session.getAttribute(MEMBER_KEY);
        if(empVO != null) {
            Notice notice = (Notice)noticeServiceExe.execute(id);
            map.put("notice", notice);
            return "/webv/noticeDetail";
        }else {
            return "/webv/login";
        }

    }

}
