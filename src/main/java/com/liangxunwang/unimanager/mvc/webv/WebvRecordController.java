package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/webvRecordController")
public class WebvRecordController extends ControllerConstants {
    @Autowired
    @Qualifier("webVRecordService")
    private ExecuteService webVRecordService;

    @RequestMapping("toDetail")
    public String toDetail(HttpSession session,String mm_msg_id,ModelMap map) throws Exception {
        EmpVO emp = (EmpVO) session.getAttribute(MEMBER_KEY);
        RecordVO recordVO = (RecordVO) webVRecordService.execute(mm_msg_id);
        String[] pics = new String[]{};
        if(!StringUtil.isNullOrEmpty(recordVO.getMm_msg_picurl())){
            //处理图片URL链接
            if(recordVO!=null && recordVO.getMm_msg_picurl()!=null){
                pics = recordVO.getMm_msg_picurl().split(",");
            }
        }
        map.put("pics", pics);
        map.put("recordVO", recordVO);

        if(emp != null){
            //说明已经登陆
            map.put("is_login", "1");
            map.put("emp", emp);
        }else{
            //说明没有登陆
            map.put("is_login", "0");
        }

        return "/webv/recordDetail";
    }


}
