package com.liangxunwang.unimanager.mvc.webv;

import com.liangxunwang.unimanager.data.PictureData;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/4/10 0010.
 */
@RequestMapping(value = "/pictureUploadController")
public class PictureUploadController extends ControllerConstants {
    private boolean message = false;     //要返回的操作结果
    private String path = "";    //要返回的文件名称
    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @RequestMapping("uploadPic")
    @ResponseBody
    public String uploadPic(HttpServletRequest request){
        try{
            message = true;   //返回的消息
            /*HttpServletRequest request = ServletActionContext.getRequest();*/
            String base64 = request.getParameter("base64");   //取得前台传来的数据
            //String size = request.getParameter("size");  //取得前台传来的大小，其它也没有什么用的

            base64 = base64.substring(base64.indexOf(",")+1);   //取得有效的图片数据来
            String dirPath = request.getRealPath("/uploads/");   //文件夹路径

            if(base64==null){
                message = false;
            }else{
                BASE64Decoder decoder = new BASE64Decoder();

                //Base64解码
                byte[] b = decoder.decodeBuffer(base64);
                for(int i=0;i<b.length;++i)
                {
                    if(b[i]<0)
                    {//调整异常数据
                        b[i]+=256;
                    }
                }
                //生成jpeg图片
                //需要换成UUIDfactory下random（）；
                String fileName = UUIDFactory.random();   //使用uuid做为唯一的主键，字段长为500，这里的长度不会超过40

                path = dirPath+"/"+fileName+".jpg";   //新生成的图片的完整的路径
                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
                path = fileName+".jpg";   //将要返回的数据直接修改为文件名

        /*        if(request.getSession().getAttribute("investor")!=null){
                    InvestorBean investorBean= (InvestorBean)request.getSession().getAttribute("investor");
                    investorBean.setAvatar("../uploads/"+path);
                    investorDAO.changeAvatar(investorBean);
                }else{

                }*/
            }
        }catch(Exception ex){
            message = false;
            ex.printStackTrace();
        }
        PictureData pd=new PictureData();
        pd.setMessage(message);
        pd.setPath(path);
        return  toJSONString(pd);
    }

}
