package com.jdkhome.blzo.service.basic;

import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.aop.log.service.ServiceLog;
import com.jdkhome.blzo.generator.model.Log;

/**
 * author linkji.
 * 日志服务
 */
public interface LogBasicService {

    //============== 添加 ==============//


    /**
     * 添加日志
     *
     * @param adminId   管理员Id
     * @param adminName 管理员名称
     * @param authjUri  authj地址
     * @param authjName authjName
     * @param paramers  参数(json)
     * @param ip        ip
     * @return
     */
    @ServiceLog("添加日志")
    Integer addLog(Integer adminId, String adminName, String authjUri, String authjName, String paramers, String ip);

    //============== 修改 ==============//

    //============== 删除 ==============//

    //============== 查询接口 ==============//

    /**
     * 获取日志通过Id
     *
     * @param logId
     * @return
     */
    @ServiceLog("获取日志通过Id")
    Log getLog(Integer logId);


    /**
     * 分页查询日志
     *
     * @param nickName  用户昵称
     * @param authjUri  权限Uri
     * @param authjName 权限Name
     * @param page
     * @param size
     * @return
     */
    @ServiceLog("分页查询日志")
    PageInfo<Log> getLogWithPage(String nickName, String authjUri, String authjName, Integer page, Integer size);

}
