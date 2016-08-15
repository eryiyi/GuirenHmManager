package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.ReportObj;

/**
 * Created by Administrator on 2016/2/14.
 */
public class ReportVO extends ReportObj{
    private String msgCont;
    private String empName;
    private String empNameReport;
    private String managerName;

    public String getMsgCont() {
        return msgCont;
    }

    public void setMsgCont(String msgCont) {
        this.msgCont = msgCont;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameReport() {
        return empNameReport;
    }

    public void setEmpNameReport(String empNameReport) {
        this.empNameReport = empNameReport;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
