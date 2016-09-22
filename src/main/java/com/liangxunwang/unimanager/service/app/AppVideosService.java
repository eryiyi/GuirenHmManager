package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.VideosDao;
import com.liangxunwang.unimanager.mvc.vo.VideosVO;
import com.liangxunwang.unimanager.query.VideosQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/3.
 */
@Service("appVideosService")
public class AppVideosService implements ListService {
    @Autowired
    @Qualifier("videosDao")
    private VideosDao videosDao;

    @Override
    public Object list(Object object) throws ServiceException {
        VideosQuery query = (VideosQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getIndex()*query.getSize();

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getTime_is())){
            map.put("time_is", query.getTime_is());
        }
        if(!StringUtil.isNullOrEmpty(query.getFavour_is())){
            map.put("favour_is", query.getFavour_is());
        }
        if(!StringUtil.isNullOrEmpty(query.getVideo_type_id())){
            map.put("video_type_id", query.getVideo_type_id());
        }

        List<VideosVO> list = new ArrayList<VideosVO>();

        if("1".equals(query.getTime_is())){
            //按时间排序
            list = videosDao.lists(map);
        }else if("1".equals(query.getFavour_is())){
            list = videosDao.lists2(map);
        }else {
            list = videosDao.lists(map);
        }

        for (VideosVO vo : list){
            if (vo.getPicUrl().startsWith("upload")) {
                vo.setPicUrl(Constants.URL + vo.getPicUrl());
            }else {
                vo.setPicUrl(Constants.QINIU_URL + vo.getPicUrl());
            }


//            if (vo.getVideoUrl().startsWith("upload")) {
//                vo.setVideoUrl(Constants.URL + vo.getVideoUrl());
//            }else {
//                vo.setVideoUrl(Constants.QINIU_URL + vo.getVideoUrl());
//            }
            if(!StringUtil.isNullOrEmpty(vo.getVideoUrl())){
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(vo!=null && vo.getVideoUrl()!=null){
                    pics = vo.getVideoUrl().split(",");
                }
                for (int i=0; i<pics.length; i++){
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                vo.setVideoUrl(buffer.toString());
            }


            vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
        }
        return new Object[]{list, 0};
    }


}
