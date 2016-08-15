package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LogoObj;
import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.model.ReportObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.mvc.vo.ReportVO;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.query.ReportQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/report")
public class ReportController extends ControllerConstants {
    @Autowired
    @Qualifier("reportService")
    private ListService reportService;

    @Autowired
    @Qualifier("reportService")
    private ExecuteService reportServiceExecute;


    @Autowired
    @Qualifier("reportService")
    private UpdateService reportServiceUpdate;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @RequestMapping("list")
    public String listQiugou(HttpSession session,ModelMap map, ReportQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) reportService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //日志记录
        logoService.save(new LogoObj("查看举报信息", manager.getMm_manager_id()));
        return "report/list";
    }


    @RequestMapping("detail")
    public String add(ModelMap map, String id) throws Exception {
        ReportVO reportVO = (ReportVO) reportServiceExecute.execute(id);
        map.put("reportVO", reportVO);
        return "/report/detail";
    }

    //更改数据
    @RequestMapping("/update")
    @ResponseBody
    public String updateEmp( HttpSession session, String mm_report_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            ReportObj reportObj = new ReportObj();
            reportObj.setMm_manager_id(manager.getMm_manager_id());
            reportObj.setMm_report_id(mm_report_id);
            reportServiceUpdate.update(reportObj);
            //日志记录
            logoService.save(new LogoObj("处理举报信息："+reportObj.getMm_report_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("reportExcelService")
    private ExecuteService reportExcelService;

    @RequestMapping("daochuAll")
    @ResponseBody
    public String daochuAll(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) reportExcelService.execute(objects);
            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);

    }

}
