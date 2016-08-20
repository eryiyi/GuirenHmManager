package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.KefuTelDao;
import com.liangxunwang.unimanager.dao.NoticeDao;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.mvc.vo.KefuVO;
import com.liangxunwang.unimanager.query.EmpQuery;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.query.NoticeQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.BaiduPush;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("noticeService")
public class NoticeService implements ListService,SaveService, ExecuteService{

    @Autowired
    @Qualifier("noticeDao")
    private NoticeDao noticeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NoticeQuery query = (NoticeQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        List<Notice> lists = noticeDao.lists(map);
        long count = noticeDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Notice notice = (Notice) object;
        notice.setMm_notice_id(UUIDFactory.random());
        notice.setDateline(DateUtil.getDateAndTime());
        notice.setIs_del("0");
        noticeDao.save(notice);
        //发通告
        BaiduPush.PushMsgToAll(notice);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        Notice notice = noticeDao.findById((String) object);
        return notice;
    }

}
