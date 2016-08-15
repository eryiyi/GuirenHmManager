package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.model.Record;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import com.liangxunwang.unimanager.mvc.vo.RecordVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("recordDao")
public interface RecordDao {

    /**
     * 查询求购、供应信息
     */
    List<RecordVO> listRecordVo(Map<String, Object> map);

    //查询关注区域的信息
    List<RecordVO> listRecordVoArea(Map<String, Object> map);

    //竞价排名
    List<RecordVO> listRecordVoTop(Map<String, Object> map);

    long count(Map<String, Object> map);

    /**
     * 保存一条信息
     * @param record
     */
    void save(Record record);

    /**
     * 根据ID查找动态
     * @return
     */
    RecordVO findById(String id);

    //删除动态
   void deleteById(String mm_msg_id);

    //更新置顶状态
    void updateTop(Record record);


    //查询用户今天发布的信息数量
    long countbyEmpId(Map<String, Object> map);
}
