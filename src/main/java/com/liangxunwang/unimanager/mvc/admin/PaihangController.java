package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.query.*;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
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
import java.util.List;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/paihang")
public class PaihangController extends ControllerConstants {
    @Autowired
    @Qualifier("paihangService")
    private ListService recordService;

    @Autowired
    @Qualifier("logoService")
    private SaveService logoService;

    @Autowired
    @Qualifier("paihangService")
    private DeleteService recordServiceDele;

    @Autowired
    @Qualifier("paihangService")
    private ExecuteService recordServiceExer;

    @Autowired
    @Qualifier("paihangService")
    private UpdateService recordServiceUpdate;

    @Autowired
    @Qualifier("paihangService")
    private SaveService paihangServiceSave;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, PaihangQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) recordService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //日志记录
        logoService.save(new LogoObj("查看排行榜", manager.getMm_manager_id()));
        return "paihang/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_paihang_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        recordServiceDele.delete(mm_paihang_id);
        //日志记录
        logoService.save(new LogoObj("删除排行榜："+mm_paihang_id, manager.getMm_manager_id()));
        return toJSONString(SUCCESS);
    }

    @RequestMapping("toDetail")
    public String add(ModelMap map, String mm_paihang_id) throws Exception {
        PaihangObjVO recordVO = (PaihangObjVO) recordServiceExer.execute(mm_paihang_id);
        map.put("recordVO", recordVO);
        return "/paihang/detail";
    }

    //更改数据
    @RequestMapping("/update")
    @ResponseBody
    public String updateEmp( HttpSession session, PaihangObj paihangObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            recordServiceUpdate.update(paihangObj);
            //日志记录
            logoService.save(new LogoObj("更新排行榜信息："+paihangObj.getMm_paihang_id(), manager.getMm_manager_id()));
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("paihangExcelService")
    private ExecuteService paihangExcelService;

    @RequestMapping("daochuAll")
    @ResponseBody
    public String daochuAll(HttpSession session,String ids, HttpServletRequest request) {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;

            String fileName = (String) paihangExcelService.execute(objects);
            //日志记录
            logoService.save(new LogoObj("到处排行榜数据", manager.getMm_manager_id()));
            DataTip tip = new DataTip();
            tip.setData(fileName);

            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }


    /**
     * 添加排行榜
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpSession session, PaihangObj paihangObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            paihangServiceSave.save(paihangObj);
            //日志记录
            logoService.save(new LogoObj("添加排行榜", manager.getMm_manager_id()));
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("Has_exist")){
                //该用户已经添加到排行榜不能重复添加
                return toJSONString(ERROR_2);
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }


    //-------------------每天凌晨执行，查询是否有过期的排行商户--------------------------
    public String update(){
        updatePaihangVip();
        return null;
    }

    @Autowired
    @Qualifier("paihangUpdateVipService")
    private UpdateService paihangUpdateVipService;

    @RequestMapping("/updatePaihangVip")
    @ResponseBody
    public String updatePaihangVip(){
        try {
            paihangUpdateVipService.update("");
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    //-------------------------------------------------
}
