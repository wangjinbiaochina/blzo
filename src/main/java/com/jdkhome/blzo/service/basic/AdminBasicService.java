package com.jdkhome.blzo.service.basic;

import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.aop.log.service.ServiceLog;
import com.jdkhome.blzo.generator.model.Admin;

import java.util.List;

/**
 * Created by jdk on 17/11/16.
 * 管理员管理业务接口
 */
public interface AdminBasicService {

    //============== 添加 ==============//


    /**
     * 添加管理员
     *
     * @param username 登录名
     * @param password 密码
     * @param nickName
     * @param phone
     * @param remark
     * @return
     */
    @ServiceLog("添加管理员")
    Integer addAdmin(String username, String password, String nickName, String phone, String remark);

    //============== 修改 ==============//

    /**
     * 修改管理员
     *
     * @param adminId
     * @param username 登录名
     * @param password 密码
     * @param nickName
     * @param phone
     * @param remark
     * @return
     */
    @ServiceLog("修改管理员")
    Integer editAdmin(Integer adminId, String username, String password, String nickName, String phone, String remark);

    //============== 删除 ==============//

    /**
     * 删除管理员
     *
     * @param adminId
     * @return
     */
    @ServiceLog("删除管理员")
    Integer delAdmin(Integer adminId);

    //============== 查询接口 ==============//

    /**
     * 获取管理员通过Id
     *
     * @param adminId
     * @return
     */
    @ServiceLog("获取管理员通过Id")
    Admin getAdminById(Integer adminId);

    /**
     * 获取管理员通过username
     *
     * @param username
     * @return
     */
    @ServiceLog("获取管理员通过username")
    Admin getAdminByUsername(String username);

    /**
     * 分页查询管理员
     *
     * @param page
     * @param size
     * @return
     */
    @ServiceLog("分页查询管理员")
    PageInfo<Admin> getAdminsWithPage(String username, String nickName, String phone, Integer page, Integer size);

    /**
     * 获取所有管理员
     *
     * @return
     */
    @ServiceLog("获取所有管理员")
    List<Admin> getAllAdmin(String username, String nickName, String phone);


}
