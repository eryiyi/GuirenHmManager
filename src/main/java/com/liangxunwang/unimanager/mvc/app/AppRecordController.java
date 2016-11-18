package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.CommentVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.mvc.vo.ZanVO;
import com.liangxunwang.unimanager.query.CommentQuery;
import com.liangxunwang.unimanager.query.RecordQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppRecordController extends ControllerConstants {

    @Autowired
    @Qualifier("appRecordService")
    private ListService recordListService;


    @Autowired
    @Qualifier("appRecordService")
    private SaveService recordSaveService;

    @Autowired
    @Qualifier("recordService")
    private DeleteService deleteRecordService;

    @Autowired
    @Qualifier("appRecordService")
    private FindService findRecordService;

    //首页获得求购供应信息
    @RequestMapping(value = "/recordList", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRecord(RecordQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            Object[] results = (Object[]) recordListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }
    //个人主页获得求购供应信息
    @RequestMapping(value = "/recordListById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String recordListById(RecordQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            Object[] results = (Object[]) recordListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }

    @RequestMapping(value = "/sendRecord", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String save(Record record, HttpSession session){
        try {
            RecordVO recordAdd = (RecordVO) recordSaveService.save(record);//保存信息
            DataTip tip = new DataTip();
            tip.setData(recordAdd);
            return toJSONString(tip);
        }catch (ServiceException e){
            if (e.getMessage().equals("HAS_PUBLISH")){
                return toJSONString(ERROR_2);
            }else if(e.getMessage().equals("HAS_FULL")){
                return toJSONString(ERROR_3);
            }else if(e.getMessage().equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }
            else{
                return toJSONString(ERROR_1);
            }
        }
    }

    @RequestMapping(value = "/getRecordById", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getRecordById(String id,String accessToken){
        try {
            RecordVO record = (RecordVO) findRecordService.findById(id);
            DataTip tip = new DataTip();
            tip.setData(record);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 通过上下文来取工程路径
     *
     * @return
     * @throws Exception
     */
    private String getAbsolutePathByContext(HttpSession session) throws Exception {

        String webPath = session.getServletContext().getRealPath("/");

        webPath = webPath.replaceAll("[\\\\\\/]WEB-INF[\\\\\\/]classes[\\\\\\/]?", "/");
        webPath = webPath.replaceAll("[\\\\\\/]+", "/");
        webPath = webPath.replaceAll("%20", " ");

        if (webPath.matches("^[a-zA-Z]:.*?$")) {

        } else {
            webPath = "/" + webPath;
        }

        webPath += "/";
        webPath = webPath.replaceAll("[\\\\\\/]+", "/");
        return webPath;

    }

    /**
     * 通过类路径来取工程路径
     *
     * @return
     * @throws Exception
     */
    private String getAbsolutePathByClass(HttpSession session) throws Exception {
        String webPath = this.getClass().getResource("/").getPath().replaceAll("^\\/", "");
        webPath = webPath.replaceAll("[\\\\\\/]WEB-INF[\\\\\\/]classes[\\\\\\/]?", "/");
        webPath = webPath.replaceAll("[\\\\\\/]+", "/");
        webPath = webPath.replaceAll("%20", " ");

        if (webPath.matches("^[a-zA-Z]:.*?$")) {

        } else {
            webPath = "/" + webPath;
        }

        webPath += "/";
        webPath = webPath.replaceAll("[\\\\\\/]+", "/");

        return webPath;
    }
    private String getAbsolutePathByResource(HttpSession session) throws Exception {
        URL url = session.getServletContext().getResource("/");
        String path = new File(url.toURI()).getAbsolutePath();
        if (!path.endsWith("\\") && !path.endsWith("/")) {
            path += File.separator;
        }
        return path;
    }

    public String init(HttpSession session) throws ServletException {
        String webPath = null;
        try {
            webPath = getAbsolutePathByContext(session);
        } catch (Exception e) {
        }

        // 在weblogic 11g 上可能无法从上下文取到工程物理路径，所以改为下面的
        if (webPath == null) {
            try {
                webPath = getAbsolutePathByClass(session);
            } catch (Exception e) {
            }
        }

        if (webPath == null) {
            try {
                webPath = getAbsolutePathByResource(session);
            } catch (Exception e) {
            }
        }
        return webPath;
    }

    public boolean processImg(String veido_path,String ffmpeg_path) {
        File file = new File(veido_path);
        if (!file.exists()) {
            System.err.println("路径[" + veido_path + "]对应的视频文件不存在!");
            return false;
        }
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpeg_path);
        commands.add("-i");
        commands.add(veido_path);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-ss");
        commands.add("1");//这个参数是设置截取视频多少秒时的画面
        //commands.add("-t");
        //commands.add("0.001");
        commands.add("-s");
        commands.add("700x525");
        commands.add(veido_path.substring(0, veido_path.lastIndexOf(".")).replaceFirst("vedio", "file") + ".jpg");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            System.out.println("截取成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/deleteRecordById")
    @ResponseBody
    public String deleteRecordById(String recordId){
        try {
            deleteRecordService.delete(recordId);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("commentService")
    private ListService listCommentService;

    @Autowired
    @Qualifier("zanService")
    private ListService zanListService;


    @RequestMapping(value = "/viewRecord",  produces = "text/plain;charset=UTF-8;")
    public String viewRecord(String id, ModelMap map){
        try {
//            Object[] params = new Object[]{recordId};
            RecordVO record  = (RecordVO) findRecordService.findById(id);
            map.put("record", record);
            if (!StringUtil.isNullOrEmpty(record.getMm_msg_picurl())){
                String[] pics = record.getMm_msg_picurl().split(",");
                List<String> list = new ArrayList<String>(pics.length);
                for (int i=0; i<pics.length; i++){
                    list.add(i, pics[i]);
                }
                map.put("pics", pics);
            }
            //查询该动态的评论
            CommentQuery query = new CommentQuery();
            query.setIndex(1);
            query.setSize(10);
            query.setRecordId(id);
            List<CommentVO> list = (List<CommentVO>) listCommentService.list(query);
            map.put("list", list);

            //查询该动态的赞
            List<ZanVO> listZan = (List<ZanVO>) zanListService.list(id);
            map.put("listZan", listZan);

//            //查询精彩推荐
//            RecordQuery query1 = new RecordQuery();
//            query1.setIndex(1);
//            query1.setSize(20);
//            query1.setSchoolId(recordId);
//            List<RecordVO> listRecord = new ArrayList<RecordVO>();
//            List<RecordVO> listRecord1 = (List<RecordVO>) recordOneService.list(query1);
//            if(listRecord1 != null){
//                for (RecordVO recordVO:listRecord1){
//                    //
//                    if(recordVO.getRecordPicUrl() != null && !"".equals(recordVO.getRecordPicUrl())){
//                        String[] str = recordVO.getRecordPicUrl().split(",");
//                        if(str != null && str.length >0){
//                            recordVO.setRecordPicUrl(str[0]);
//                        }
//                        listRecord.add(recordVO);
//                    }
//                }
//            }
//            map.put("listRecord", listRecord);
            map.put("baseurl", Constants.URL);
            return "/record/viewRecord";
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("appRecordPicsService")
    private ListService appRecordPicsService;


    //获取会员动态的图片
    @RequestMapping(value = "/recordPics", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String recordPics(RecordQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            List<String> strPics = (List<String>) appRecordPicsService.list(query);
            DataTip tip = new DataTip();
            tip.setData(strPics);
            return toJSONString(tip);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }
}
