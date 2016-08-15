package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AccessTokenDao;
import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.dao.ReportDao;
import com.liangxunwang.unimanager.model.AccessToken;
import com.liangxunwang.unimanager.model.Level;
import com.liangxunwang.unimanager.model.ReportObj;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.mvc.vo.ReportVO;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.query.ReportQuery;
import com.liangxunwang.unimanager.service.*;
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
 * Created by zhl on 2015/3/3.
 */
@Service("reportService")
public class ReportService implements ListService,SaveService,ExecuteService,UpdateService {
    @Autowired
    @Qualifier("reportDao")
    private ReportDao reportDao;

    @Override
    public Object list(Object object) throws ServiceException {
        ReportQuery query = (ReportQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex() * query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getIs_use())) {
            map.put("is_use", query.getIs_use());
        }

        List<ReportVO> lists = reportDao.lists(map);
        long count = reportDao.count(map);

        return new Object[]{lists, count};
    }

    @Autowired
    @Qualifier("accessTokenDao")
    private AccessTokenDao accessTokenDao;

    @Override
    public Object save(Object object) throws ServiceException {
        ReportObj level = (ReportObj) object;
        //判断accesstoken是否存在 是否是最新的
        if(!StringUtil.isNullOrEmpty(level.getAccessToken())){
            //不为空，判断是否单点登录
            AccessToken accessToken = accessTokenDao.findByToken(level.getAccessToken());
            if(accessToken == null){
                throw new ServiceException("accessTokenNull");
            }
        }else {
            throw new ServiceException("accessTokenNull");
        }
        level.setMm_report_id(UUIDFactory.random());
        level.setDateline(DateUtil.getDateAndTime());//时间戳
        level.setIs_use("0");
        reportDao.save(level);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return reportDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        ReportObj reportObj = (ReportObj) object;
        reportObj.setEnd_dateline(DateUtil.getDateAndTime());//处理时间
        reportObj.setIs_use("1");
        reportDao.update(reportObj);
        return null;
    }
}
