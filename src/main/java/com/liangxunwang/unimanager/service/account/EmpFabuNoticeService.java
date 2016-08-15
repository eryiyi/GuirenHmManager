package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.NoticeDao;
import com.liangxunwang.unimanager.dao.VipEndTimeDao;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.model.VipEndTime;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.BaiduPush;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量发布到期通知
 */
@Service("empFabuNoticeService")
public class EmpFabuNoticeService implements ExecuteService{
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Autowired
    @Qualifier("vipEndTimeDao")
    private VipEndTimeDao vipEndTimeDao;

    @Autowired
    @Qualifier("noticeDao")
    private NoticeDao noticeDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        String mm_manager_id = (String) objects[1];
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(ids)){
            List<VipEndTime> lists = vipEndTimeDao.lists(map);//这是设置好的到期提醒语句
            VipEndTime vipEndTime = null;//提醒语句
            if(lists != null && lists.size() > 0){
                vipEndTime = lists.get(0);
            }
            if(vipEndTime != null){
                String[] arrs = ids.split(",");
                for(int i=0;i<arrs.length;i++){
                    //批量发布到期通知
                    EmpVO empVO = empDao.findById(arrs[i]);//查询到期的用户
                    Notice notice = new Notice();
                    notice.setMm_notice_id(UUIDFactory.random());
                    notice.setMm_manager_id(mm_manager_id);
                    notice.setMm_notice_title(vipEndTime.getMm_endtime_vip_title());
                    notice.setMm_notice_content(vipEndTime.getMm_endtime_vip_content());
                    notice.setDateline(DateUtil.getDateAndTime());
                    notice.setIs_del("0");
                    notice.setMm_emp_id(empVO.getMm_emp_id());
                    noticeDao.save(notice);//添加公告--针对个人的
//                (Notice notice,String channelId)
                    BaiduPush.PushMsgToSingleDevice(notice, (empVO.getChannelId()==null?"":empVO.getChannelId()));
                }
            }else {
                return null;
            }

        }
        return null;
    }




}
