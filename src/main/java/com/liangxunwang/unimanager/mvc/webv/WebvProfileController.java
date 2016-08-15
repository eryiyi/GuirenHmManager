package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpAdVO;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.query.EmpAdQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
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
@RequestMapping("/webvProfile")
public class WebvProfileController extends ControllerConstants {

    @Autowired
    @Qualifier("empService")
    private ExecuteService empService;


    @Autowired
    @Qualifier("empAdService")
    private ListService empAdService;

    @Autowired
    @Qualifier("webVRecordService")
    private ListService recordService;


    @RequestMapping("toProfile")
    public String add(ModelMap map,RecordQuery query, Page page) throws Exception {
        if(!StringUtil.isNullOrEmpty(query.getMm_emp_id())){
            EmpVO empVO = (EmpVO) empService.execute(query.getMm_emp_id());
            map.put("emp", empVO);
            //获得用户的轮播图
            EmpAdQuery queryad = new EmpAdQuery();
            queryad.setMm_emp_id(empVO.getMm_emp_id());
            List<EmpAdVO> list = (List<EmpAdVO>) empAdService.list(queryad);
            if(list != null && list.size() == 0){
                //说明没有广告图，添加一个默认的
                EmpAdVO adVO = new EmpAdVO();
                adVO.setMm_emp_ad_pic(Constants.URL + Constants.DEFAULT_SERVICE_TOP_BG);
                adVO.setMm_emp_ad_url(Constants.URL + Constants.DEFAULT_DOWNLOAD_URL);
                list.add(adVO);
            }
            map.put("empAdVO", list);
            //查询用户动态

            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
            query.setMm_msg_type("0");

            Object[] results = (Object[]) recordService.list(query);
            map.put("list", results[0]);
            long count = (Long) results[1];
            page.setCount(count);
            page.setPageCount(calculatePageCount(query.getSize(), count));
            map.addAttribute("page", page);
            map.addAttribute("query", query);
        }
        return "/webv/profile";

    }


    @Autowired
    @Qualifier("webProfileService")
    private UpdateService webProfileService;

    /**
     * 完善个人资料
     * @param member  会员对象
     * @return
     */
    @RequestMapping("/webMemberUpdateProfile")
    @ResponseBody
    public String webMemberUpdateProfile(HttpSession session,Emp member){
        try {
            EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
            if(emp != null){
                member.setMm_emp_id(emp.getMm_emp_id());
                webProfileService.update(member);
                ((EmpVO) session.getAttribute(MEMBER_KEY)).setIs_upate_profile("1");
            }else {
                return toJSONString(ERROR_9);//请先登录
            }
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals(Constants.SAVE_ERROR)){
                //更新失败
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }

    @RequestMapping("toUpdateProfile")
    public String add() {
        return "/webv/updateProfile";
    }
}
