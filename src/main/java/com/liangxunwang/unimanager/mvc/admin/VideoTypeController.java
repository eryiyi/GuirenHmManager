package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.query.*;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/videoTypeController")
public class VideoTypeController extends ControllerConstants {

    @Autowired
    @Qualifier("videoTypeObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("videoTypeObjService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("videoTypeObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("videoTypeObjService")
    private UpdateService levelServiceSaveUpdate;


    @RequestMapping("list")
    public String list(ModelMap map, VideoTypeQuery query){
        List<VideoTypeObj> list = (List<VideoTypeObj>) levelService.list(query);
        map.put("list", list);
        return "/videoType/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/videoType/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(VideoTypeObj videoTypeObj){
        levelServiceSave.save(videoTypeObj);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(ModelMap map, String video_type_id) throws Exception {
        VideoTypeObj videoTypeObj = (VideoTypeObj) levelServiceSaveExe.execute(video_type_id);
        map.put("videoTypeObj", videoTypeObj);
        return "/videoType/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(VideoTypeObj videoTypeObj){
        try {
            levelServiceSaveUpdate.update(videoTypeObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
