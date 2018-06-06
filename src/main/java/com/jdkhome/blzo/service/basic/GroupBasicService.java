package com.jdkhome.blzo.service.basic;

import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.aop.log.service.ServiceLog;
import com.jdkhome.blzo.generator.model.Group;
import com.jdkhome.blzo.generator.model.GroupAdmin;
import com.jdkhome.blzo.generator.model.GroupAuth;
import com.jdkhome.blzo.generator.model.GroupMenu;

import java.util.List;

/**
 * Created by jdk on 17/11/16.
 * 组管理业务接口
 * 包括 组 组-管理员 组-权限 组-菜单 的管理维护
 */
public interface GroupBasicService {

    //============== 添加 ==============//

    /**
     * 添加组
     *
     * @param name
     * @param comment
     * @return
     */
    @ServiceLog("添加组")
    Integer addGroup(String name, String comment);


    /**
     * 增加组管理员关联
     *
     * @param groupId
     * @param adminId
     * @return
     */
    @ServiceLog("增加组管理员关联")
    Integer addGroupAdmin(Integer groupId, Integer adminId);

    /**
     * 增加组菜单关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @ServiceLog("增加组菜单关联")
    Integer addGroupMenu(Integer groupId, String uri);

    /**
     * 增加组权限关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @ServiceLog("增加组权限关联")
    Integer addGroupAuth(Integer groupId, String uri);

    //============== 修改 ==============//

    /**
     * 修改组
     *
     * @param groupId
     * @param name
     * @param comment
     * @return
     */
    @ServiceLog("修改组")
    Integer editGroups(Integer groupId, String name, String comment);

    //============== 删除 ==============//

    /**
     * 删除组
     *
     * @param groupId
     * @return
     */
    @ServiceLog("删除组")
    Integer delGroup(Integer groupId);

    /**
     * 删除组管理员关联
     *
     * @param groupId
     * @param adminId
     * @return
     */
    @ServiceLog("删除组管理员关联")
    Integer delGroupAdmin(Integer groupId, Integer adminId);

    /**
     * 移除组菜单关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @ServiceLog("删除组菜单关联")
    Integer delGroupMenu(Integer groupId, String uri);

    /**
     * 删除组权限关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @ServiceLog("删除组权限关联")
    Integer delGroupAuth(Integer groupId, String uri);

    //============== 查询接口 ==============//

    /**
     * 获取组通过Id
     *
     * @param groupId
     * @return
     */
    @ServiceLog("获取组通过Id")
    Group getGroupById(Integer groupId);

    /**
     * 分页查询组
     *
     * @param name
     * @param page
     * @param size
     * @return
     */
    @ServiceLog("分页查询组")
    PageInfo<Group> getGroupsWithPage(String name, Integer page, Integer size);

    /**
     * 获取所有组
     *
     * @param name
     * @return
     */
    @ServiceLog("获取所有组")
    List<Group> getAllGroup(String name);


    /**
     * 获取GroupAdmin 通过管理员Id
     *
     * @param adminId
     * @return
     */
    @ServiceLog("获取GroupAdmin 通过管理员Id")
    List<GroupAdmin> getGroupAdminByAdminId(Integer adminId);

    /**
     * 获取GroupAuth 通过组Id
     *
     * @param groupId
     * @return
     */
    @ServiceLog("获取GroupAuth 通过组Id")
    List<GroupAuth> getGroupAuthByGroupId(Integer groupId);

    /**
     * 获取组-菜单 关系 通过 组id
     *
     * @param groupId
     * @return
     */
    @ServiceLog("获取GroupMenu 通过组id")
    List<GroupMenu> getGroupMenuByGroupId(Integer groupId);

    /**
     * 获取GroupAdmin 通过组Id
     *
     * @param groupId
     * @return
     */
    @ServiceLog("获取GroupAdmin 通过组Id")
    List<GroupAdmin> getGroupAdminByGroupId(Integer groupId);

}
