package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.TvsDao;
import com.liangxunwang.unimanager.dao.VideosDao;
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
@Service("tvService")
public class TvService implements SaveService,ListService, DeleteService,FindService ,UpdateService{
    @Autowired
    @Qualifier("tvsDao")
    private TvsDao tvsDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Videos videos = (Videos) object;
        videos.setDateline(System.currentTimeMillis() + "");
        videos.setIsdel("0");
        videos.setId(UUIDFactory.random());
        tvsDao.save(videos);
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {

        VideosQuery query = (VideosQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex()*query.getSize();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if (!StringUtil.isNullOrEmpty(query.getIsdel())) {
            map.put("isdel", query.getIsdel());
        }
        List<VideosVO> list = tvsDao.lists(map);
        for (VideosVO vo : list){
            vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
        }
        long count = tvsDao.count(map);
        return new Object[]{list, count};
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String id = (String) object;
        tvsDao.delete(id);
        return null;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        String id = (String) object;
        Videos vo = tvsDao.findById(id);
        if(vo != null){
            if (vo.getPicUrl().startsWith("upload")) {
                vo.setPicUrl(Constants.URL + vo.getPicUrl());
            }else {
                vo.setPicUrl(Constants.QINIU_URL + vo.getPicUrl());
            }
            if (vo.getVideoUrl().startsWith("upload")) {
                vo.setVideoUrl(Constants.URL + vo.getVideoUrl());
            }else {
                vo.setVideoUrl(Constants.QINIU_URL + vo.getVideoUrl());
            }
        }
        return vo;
    }

    @Override
    public Object update(Object object) {
        tvsDao.update((Videos) object);
        return null;
    }
}
