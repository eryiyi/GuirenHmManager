package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.dao.ReportDao;
import com.liangxunwang.unimanager.query.BaseAreaQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("indexService")
public class IndexService implements ListService {
    @Autowired
    @Qualifier("empDao")
    private EmpDao empDao;

    @Autowired
    @Qualifier("recordDao")
    private RecordDao recordDao;

    @Autowired
    @Qualifier("reportDao")
    private ReportDao reportDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        //总共会员数量
        long memberCount = empDao.count(map);
        //今日注册会员数量
        map.put("mm_emp_regtime", DateUtil.getDateAndTimeTwo());
        long memberCountNoDay = empDao.countDay(map);
        //查询举报数量
        Map<String, Object> mapReport = new HashMap<String, Object>();
        long countReport = reportDao.countAll(mapReport);

        List<Long> list = new ArrayList<Long>();
        list.add(memberCount);
        list.add(countReport);
        list.add(memberCountNoDay);
        return list;
    }
}
