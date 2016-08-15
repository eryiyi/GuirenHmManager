package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Emp;
import com.liangxunwang.unimanager.mvc.vo.EmpDianpu;
import com.liangxunwang.unimanager.mvc.vo.EmpVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("empDao")
public interface EmpDao {
    /**
     * @param list
     */
    void saveList(List<Emp> list);

    /**
     * 查询所有的会员信息
     */
    List<EmpVO> listMemberByName(Map<String, Object> map);

    long count(Map<String,Object> map);
    //今日注册会员数量
    long countDay(Map<String,Object> map);

    //更新会员数据
    void update(Emp emp);


    //根据用户ID查询用户信息
    EmpVO findById(String mm_emp_id);

    /**
     * 根据手机号查找会员
     * @param mm_emp_mobile
     * @return
     */
    EmpVO findByMobile(String mm_emp_mobile);
    //根据环信id查询用户信息
    EmpVO findByHxId(String hxusername);

    //保存会员信息
    void save(Emp emp);

    //更新会员数据--经纬度
    void updateLoacation(Emp emp);
    //上传用户头像
    void updateCover(Emp emp);
    //根据用户iD上传头像
    void updateCoverById(Emp emp);

    //修改用户密码
    void updatePwr(Emp emp);

    //审核用户
    void updateCheck(Map<String,Object> map);

    /**
     * 查询附近商家
     */
    List<EmpVO> listsLocation(Map<String, Object> map);

    //查询会员尾数排行
    List<EmpVO> listtopnumber(Map<String, Object> map);
    //查询顶级会员
    List<EmpVO> listtopnumber2(Map<String, Object> map);

    //查询以根部开始的所有会员
    List<EmpVO> listtopnumber3(Map<String, Object> map);

    //查询会员人脉
    List<EmpVO> listsRenmai(Map<String, Object> map);

    /**
     * 根据手机号和身份证号查找会员
     * @return
     */
    EmpVO findByMobileAndCard(Map<String, Object> map);

    EmpVO findByNumber(String top_number);

    //用户登陆状态修改
    void updateLogin(Map<String,Object> map);

    //用户发布供应权限
    void updateFabugy(Map<String,Object> map);

    //发布求购权限
    void updateFabuqg(Map<String,Object> map);

    //补充资料
    void updateProfileMember(Emp emp);

    /**
     * 根据过期时间去禁用VIP
     * @param nowTime
     */
    public void updateOverTime(String nowTime);


    //删除用户
    void deleteEmp(String mm_emp_id);

    /**
     * 根据ID更新pushId
     * {id, userId, channelId, type}
     */
    void updatePushId(@Param(value = "id") String id, @Param(value = "userId") String userId, @Param(value = "channelId") String channelId, @Param(value = "type")String type);

    //更换手机号
    void updateMobile(Emp emp);
    //更改用户资料
    void editEmpById(Emp emp);
    //审核
    void updateEmpCheck(Emp emp);

    //更新背景图
    void updateBgMine(Emp emp);

    List<EmpVO> listMemberInfoByUsername(Map<String,Object> map);


    /**
     * 根据会员ID设为商家
     * @param empId
     * @param flag
     */
    void changeBusiness(@Param(value = "empId")String empId, @Param(value = "flag")String flag);
    //查询店铺
    List<EmpDianpu> listDianPu(Map<String,Object> map);
}
