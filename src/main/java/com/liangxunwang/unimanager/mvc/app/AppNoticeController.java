package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.NoticeQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppNoticeController extends ControllerConstants {

    @Autowired
    @Qualifier("noticeService")
    private ListService noticeServiceList;

    @Autowired
    @Qualifier("noticeService")
    private ExecuteService noticeServiceExe;

    //查询notice列表
    @RequestMapping(value = "/getNotices", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRecord(NoticeQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            Object[] results = (Object[]) noticeServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
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


    //查询notice详情
    @RequestMapping(value = "/getNoticesDetail", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getVipList(String id) throws Exception {
        try {
            Notice notice = (Notice) noticeServiceExe.execute(id);
            DataTip tip = new DataTip();
            tip.setData(notice);
            return toJSONString(tip);
        } catch (ServiceException e) {
            return toJSONString(ERROR_1);
        }
    }
}
