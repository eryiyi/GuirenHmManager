package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.TvsDao;
import com.liangxunwang.unimanager.model.Videos;
import com.liangxunwang.unimanager.mvc.vo.VideosVO;
import com.liangxunwang.unimanager.query.VideosQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/3.
 */
@Service("tvFindService")
public class TvFindService implements FindService {
    @Autowired
    @Qualifier("tvsDao")
    private TvsDao tvsDao;


    @Override
    public Object findById(Object object) throws ServiceException {
        String id = (String) object;
        Videos vo = tvsDao.findById(id);
        return vo;
    }

}
