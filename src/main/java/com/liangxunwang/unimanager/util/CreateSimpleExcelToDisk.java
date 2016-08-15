package com.liangxunwang.unimanager.util;

import com.liangxunwang.unimanager.model.Student;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.mvc.vo.ReportVO;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by zhanghailong on 2016/3/19.
 */
public class CreateSimpleExcelToDisk {
    /**
     * @功能：手工构建一个简单格式的Excel
     */

    public static String toExcelEmp(List<EmpVO> empVOs, HttpServletRequest request) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("会员表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("用户类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("公司");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("公司类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("公司地址");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("公司微网址");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("省");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("市");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("县");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("注册时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);
        cell.setCellValue("等级");
        cell.setCellStyle(style);
        cell = row.createCell((short) 13);
        cell.setCellValue("到期时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 14);
        cell.setCellValue("允许发布信息数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 15);
        cell.setCellValue("允许登陆");
        cell.setCellStyle(style);
        cell = row.createCell((short) 16);
        cell.setCellValue("允许发布供应");
        cell.setCellStyle(style);
        cell = row.createCell((short) 17);
        cell.setCellValue("允许发布求购");
        cell.setCellStyle(style);
        cell = row.createCell((short) 18);
        cell.setCellValue("允许查看供应");
        cell.setCellStyle(style);

        cell = row.createCell((short) 19);
        cell.setCellValue("允许查看求购");
        cell.setCellStyle(style);
        cell = row.createCell((short) 20);
        cell.setCellValue("允许查看所有信息");
        cell.setCellStyle(style);
        cell = row.createCell((short) 21);
        cell.setCellValue("是否禁用");
        cell.setCellStyle(style);
        cell = row.createCell((short) 22);
        cell.setCellValue("是否允许发布图片");
        cell.setCellStyle(style);
        cell = row.createCell((short) 23);
        cell.setCellValue("是否诚信单位");
        cell.setCellStyle(style);
        cell = row.createCell((short) 24);
        cell.setCellValue("是否苗木单位");
        cell.setCellStyle(style);
        cell = row.createCell((short) 25);
        cell.setCellValue("是否审核");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//        List list = CreateSimpleExcelToDisk.getStudent();

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            EmpVO stu = (EmpVO) empVOs.get(i);
            // 第四步，创建单元格，并设置值
//            row.createCell((short) 0).setCellValue((double) stu.getId());
            row.createCell((short) 0).setCellValue(stu.getMm_emp_mobile());
            row.createCell((short) 1).setCellValue(stu.getMm_emp_nickname());
            row.createCell((short) 2).setCellValue(stu.getMm_emp_card());

            row.createCell((short) 4).setCellValue(stu.getMm_emp_company());

            row.createCell((short) 8).setCellValue(stu.getProvinceName());
            row.createCell((short) 9).setCellValue(stu.getCityName());
            row.createCell((short) 10).setCellValue(stu.getAreaName());
            row.createCell((short) 11).setCellValue(stu.getMm_emp_regtime());
            if("0".equals(stu.getIs_login())){
                row.createCell((short) 15).setCellValue("允许");
            }else {
                row.createCell((short) 15).setCellValue("不允许");
            }
            if("0".equals(stu.getIs_use())){
                row.createCell((short) 21).setCellValue("否");
            }else {
                row.createCell((short) 21).setCellValue("是");
            }
            if("0".equals(stu.getIscheck())){
                row.createCell((short) 25).setCellValue("否");
            }else if("1".equals(stu.getIscheck())) {
                row.createCell((short) 25).setCellValue("是");
            }else if("2".equals(stu.getIscheck())) {
                row.createCell((short) 25).setCellValue("未通过");
            }
//            cell = row.createCell((short) 3);
//            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
//                    .getBirth()));
        }
        // 第六步，将文件存到指定位置
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/huiyuan_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/huiyuan_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelRecord(List<RecordVO> empVOs, HttpServletRequest request) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("信息表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("信息类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("发布者");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("发布者手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("发布者类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("公司");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("信息标题");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("信息内容");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("信息归属地");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("是否置顶");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("置顶数字");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//        List list = CreateSimpleExcelToDisk.getStudent();

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            RecordVO stu = (RecordVO) empVOs.get(i);
            // 第四步，创建单元格，并设置值
            if("0".equals(stu.getMm_msg_type())){
                row.createCell((short) 0).setCellValue("求购");
            }else {
                row.createCell((short) 0).setCellValue("供应");
            }
            row.createCell((short) 1).setCellValue(stu.getMm_emp_nickname());
            row.createCell((short) 2).setCellValue(stu.getMm_emp_mobile());
            if("0".equals(stu.getMm_msg_type())){
                row.createCell((short) 3).setCellValue("苗木经营");
            }else {
                row.createCell((short) 3).setCellValue("苗木会员");
            }
            row.createCell((short) 4).setCellValue(stu.getMm_emp_company());
            row.createCell((short) 6).setCellValue(stu.getMm_msg_content()==null?"":stu.getMm_msg_content());
            row.createCell((short) 7).setCellValue(stu.getMm_emp_company());

            if(!StringUtil.isNullOrEmpty(stu.getDateline())){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                Date date =  new Date(Long.valueOf(stu.getDateline()));
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(date);
                String timeDateline = format.format(gc.getTime());
                row.createCell((short) 8).setCellValue(timeDateline);
            }else {
                row.createCell((short) 8).setCellValue("");
            }

            if("0".equals(stu.getIs_top())){
                row.createCell((short) 9).setCellValue("否");
            }else {
                row.createCell((short) 9).setCellValue("是");
            }
            row.createCell((short) 10).setCellValue(stu.getTop_num()==null?"":stu.getTop_num());

        }
        // 第六步，将文件存到指定位置
        try
        {
//            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//            String fileName = String.valueOf(df.format(new Date()));
//            FileOutputStream fout = new FileOutputStream("D:/xinxi_"+fileName+".xls");
//            wb.write(fout);
//            fout.close();
//            return fileName;

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/xinxi_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/xinxi_"+fileName+".xls";

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelReport(List<ReportVO> empVOs, HttpServletRequest request) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("举报表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("举报人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("被举报人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("举报时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("举报内容");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("被举报信息");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("是否处理");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("处理人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("处理时间");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//        List list = CreateSimpleExcelToDisk.getStudent();

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            ReportVO stu = (ReportVO) empVOs.get(i);
            // 第四步，创建单元格，并设置值
//            row.createCell((short) 0).setCellValue((double) stu.getId());
            row.createCell((short) 0).setCellValue(stu.getEmpName());
            row.createCell((short) 1).setCellValue(stu.getEmpNameReport());
            row.createCell((short) 2).setCellValue(stu.getDateline());

            row.createCell((short) 3).setCellValue(stu.getMm_report_content());
            row.createCell((short) 4).setCellValue(stu.getMsgCont()==null?"":stu.getMsgCont());

            if("0".equals(stu.getIs_use())){
                row.createCell((short) 5).setCellValue("未处理");
            }else {
                row.createCell((short) 5).setCellValue("已处理");
            }
//            row.createCell((short) 7).setCellValue(stu.getDateline());
            row.createCell((short) 6).setCellValue(stu.getManagerName()==null?"":stu.getManagerName());
            if(!StringUtil.isNullOrEmpty(stu.getEnd_dateline())){
                row.createCell((short) 7).setCellValue(stu.getEnd_dateline());
            }else {
                row.createCell((short) 7).setCellValue("");
            }

        }
        // 第六步，将文件存到指定位置
        try
        {
//            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//            String fileName = String.valueOf(df.format(new Date()));
//            FileOutputStream fout = new FileOutputStream("D:/jubao_"+fileName+".xls");
//            wb.write(fout);
//            fout.close();
//            return fileName;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/jubao_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/jubao_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }



    public static String toExcelPaihang(List<PaihangObjVO> empVOs, HttpServletRequest request) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("金牌商家排行榜");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("商家姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("商家手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("商家类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("公司名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("公司地址");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("是否上榜");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("置顶数字");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("退榜时间");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//        List list = CreateSimpleExcelToDisk.getStudent();

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            PaihangObjVO stu = (PaihangObjVO) empVOs.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(stu.getMm_emp_nickname());
            row.createCell((short) 1).setCellValue(stu.getMm_emp_mobile());
            if("0".equals(stu.getMm_emp_type())){
                row.createCell((short) 2).setCellValue("苗木经营");
            }else {
                row.createCell((short) 2).setCellValue("苗木会员");
            }

            row.createCell((short) 3).setCellValue(stu.getMm_emp_company());
            row.createCell((short) 4).setCellValue(stu.getMm_emp_company_address()==null?"":stu.getMm_emp_company_address());

            if("0".equals(stu.getIs_del())){
                row.createCell((short) 5).setCellValue("是");
            }else {
                row.createCell((short) 5).setCellValue("否");
            }
            row.createCell((short) 6).setCellValue(stu.getTop_num()==null?"":stu.getTop_num());
            if(!StringUtil.isNullOrEmpty(stu.getEnd_time())){
                row.createCell((short) 7).setCellValue(stu.getEnd_time());
            }else {
                row.createCell((short) 7).setCellValue("无");
            }

        }
        // 第六步，将文件存到指定位置
        try
        {
//            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//            String fileName = String.valueOf(df.format(new Date()));
//            FileOutputStream fout = new FileOutputStream("D:/jubao_"+fileName+".xls");
//            wb.write(fout);
//            fout.close();
//            return fileName;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/paihang_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/paihang_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
