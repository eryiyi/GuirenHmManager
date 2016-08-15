package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.EmpDao;
import com.liangxunwang.unimanager.dao.RecordDao;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.CreateSimpleExcelToDisk;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Service("recordExcelService")
public class RecordExcelService implements ExecuteService{
    @Autowired
    @Qualifier("recordDao")
    private RecordDao recordDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            //查询这些用户的数据
            List<RecordVO> empVOs = new ArrayList<RecordVO>();
            for(int i=0;i<arrs.length;i++){
                RecordVO empVO = recordDao.findById(arrs[i]);
                if(empVO != null && !StringUtil.isNullOrEmpty(empVO.getMm_emp_id())){
                    empVOs.add(empVO);
                }
            }
            String fileName = CreateSimpleExcelToDisk.toExcelRecord(empVOs,request);
            return fileName;
        }
        return null;
    }




}
