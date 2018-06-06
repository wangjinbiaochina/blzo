package com.jdkhome.blzo.service.basic.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.aop.authj.AuthjManager;
import com.jdkhome.blzo.common.constant.SqlTemplate;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.generator.dao.GroupAdminMapper;
import com.jdkhome.blzo.generator.dao.GroupAuthMapper;
import com.jdkhome.blzo.generator.dao.GroupMapper;
import com.jdkhome.blzo.generator.dao.GroupMenuMapper;
import com.jdkhome.blzo.generator.model.*;
import com.jdkhome.blzo.service.basic.GroupBasicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jdk on 18/1/6.
 */
@Service
public class GroupBasicServiceImpl implements GroupBasicService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupAdminMapper groupAdminMapper;

    @Autowired
    GroupAuthMapper groupAuthMapper;

    @Autowired
    GroupMenuMapper groupMenuMapper;

    @Autowired
    AuthjManager authjManager;

    /**
     * 添加组
     *
     * @param name
     * @param remark
     * @return
     */
    @Override
    public Integer addGroup(String name, String remark) {

        //入参验证
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(remark)) {
            logger.error("添加组->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        Group group = new Group();
        group.setName(name);
        group.setRemark(remark);
        group.setCreateAdminId(authjManager.getUserId());

        return groupMapper.insertSelective(group);
    }

    /**
     * 增加组管理员关联
     *
     * @param groupId
     * @param adminId
     * @return
     */
    @Override
    public Integer addGroupAdmin(Integer groupId, Integer adminId) {

        //入参验证
        if (groupId == null || adminId == null) {
            logger.error("增加组管理员关联->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.setGroupId(groupId);
        groupAdmin.setAdminId(adminId);
        groupAdmin.setCreateAdminId(authjManager.getUserId());

        return groupAdminMapper.insertSelective(groupAdmin);
    }

    /**
     * 增加组菜单关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @Override
    public Integer addGroupMenu(Integer groupId, String uri) {
        //入参验证
        if (groupId == null || StringUtils.isEmpty(uri)) {
            logger.error("增加组菜单关联->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupMenu groupMenu = new GroupMenu();
        groupMenu.setGroupId(groupId);
        groupMenu.setUri(uri);
        groupMenu.setCreateAdminId(authjManager.getUserId());

        return groupMenuMapper.insertSelective(groupMenu);
    }

    /**
     * 增加组权限关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @Override
    public Integer addGroupAuth(Integer groupId, String uri) {
        //入参验证
        if (groupId == null || StringUtils.isEmpty(uri)) {
            logger.error("增加组权限关联->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAuth groupAuth = new GroupAuth();
        groupAuth.setGroupId(groupId);
        groupAuth.setUri(uri);
        groupAuth.setCreateAdminId(authjManager.getUserId());

        return groupAuthMapper.insertSelective(groupAuth);
    }

    /**
     * 修改组
     *
     * @param groupId
     * @param name
     * @param remark
     * @return
     */
    @Override
    public Integer editGroups(Integer groupId, String name, String remark) {
        if (groupId == null) {
            logger.error("修改组->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        Group group = new Group();

        group.setId(groupId);

        if (StringUtils.isNotEmpty(name)) {
            group.setName(name);
        }

        if (StringUtils.isNotEmpty(remark)) {
            group.setRemark(remark);
        }

        return groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 删除组
     *
     * @param groupId
     * @return
     */
    @Override
    public Integer delGroup(Integer groupId) {
        if (groupId == null) {
            logger.error("删除组->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        return groupMapper.deleteByPrimaryKey(groupId);
    }

    /**
     * 删除组管理员关联
     *
     * @param groupId
     * @param adminId
     * @return
     */
    @Override
    public Integer delGroupAdmin(Integer groupId, Integer adminId) {

        //入参验证
        if (groupId == null || adminId == null) {
            logger.error("删除组管理员关联->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAdminExample example = new GroupAdminExample();
        example.createCriteria().andGroupIdEqualTo(groupId).andAdminIdEqualTo(adminId);

        return groupAdminMapper.deleteByExample(example);
    }

    /**
     * 移除组菜单关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @Override
    public Integer delGroupMenu(Integer groupId, String uri) {

        //入参验证
        if (groupId == null || StringUtils.isEmpty(uri)) {
            logger.error("移除组菜单关联->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupMenuExample example = new GroupMenuExample();
        example.createCriteria().andGroupIdEqualTo(groupId).andUriEqualTo(uri);

        return groupMenuMapper.deleteByExample(example);
    }

    /**
     * 删除组权限关联
     *
     * @param groupId
     * @param uri
     * @return
     */
    @Override
    public Integer delGroupAuth(Integer groupId, String uri) {

        //入参验证
        if (groupId == null || StringUtils.isEmpty(uri)) {
            logger.error("删除组权限关联->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAuthExample example = new GroupAuthExample();
        example.createCriteria().andGroupIdEqualTo(groupId).andUriEqualTo(uri);

        return groupAuthMapper.deleteByExample(example);
    }

    /**
     * 获取组通过Id
     *
     * @param groupId
     * @return
     */
    @Override
    public Group getGroupById(Integer groupId) {
        return groupMapper.selectByPrimaryKey(groupId);
    }


    public GroupExample getExample(String name) {

        GroupExample example = new GroupExample();
        GroupExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }

        example.setOrderByClause(SqlTemplate.ORDER_BY_ID_DESC);

        return example;
    }

    /**
     * 分页查询组
     *
     * @param name
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Group> getGroupsWithPage(String name, Integer page, Integer size) {

        //入参验证
        if (page == null || size == null) {
            logger.error("分页查询组->分页参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupExample example = this.getExample(name);
        PageHelper.startPage(page, size);

        return new PageInfo<>(groupMapper.selectByExample(example));
    }

    /**
     * 获取所有组
     *
     * @param name
     * @return
     */
    @Override
    public List<Group> getAllGroup(String name) {
        GroupExample example = this.getExample(name);
        return groupMapper.selectByExample(example);
    }

    /**
     * 获取GroupAdmin 通过管理员Id
     *
     * @param adminId
     * @return
     */
    @Override
    public List<GroupAdmin> getGroupAdminByAdminId(Integer adminId) {

        if (adminId == null) {
            logger.error("获取GroupAdmin 通过管理员Id->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAdminExample example = new GroupAdminExample();
        example.createCriteria().andAdminIdEqualTo(adminId);

        return groupAdminMapper.selectByExample(example);
    }

    /**
     * 获取GroupAuth 通过组Id
     *
     * @param groupId
     * @return
     */
    @Override
    public List<GroupAuth> getGroupAuthByGroupId(Integer groupId) {

        if (groupId == null) {
            logger.error("获取GroupAuth 通过组Id->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAuthExample example = new GroupAuthExample();
        example.createCriteria().andGroupIdEqualTo(groupId);

        return groupAuthMapper.selectByExample(example);
    }

    /**
     * 获取组-菜单 关系 通过 组id
     *
     * @param groupId
     * @return
     */
    @Override
    public List<GroupMenu> getGroupMenuByGroupId(Integer groupId) {

        if (groupId == null) {
            logger.error("获取GroupMenu 通过组Id->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupMenuExample example = new GroupMenuExample();
        example.createCriteria().andGroupIdEqualTo(groupId);

        return groupMenuMapper.selectByExample(example);
    }

    /**
     * 获取GroupAdmin 通过组Id
     *
     * @param groupId
     * @return
     */
    @Override
    public List<GroupAdmin> getGroupAdminByGroupId(Integer groupId) {

        if (groupId == null) {
            logger.error("获取GroupAdmin 通过组Id->参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        GroupAdminExample example = new GroupAdminExample();
        example.createCriteria().andGroupIdEqualTo(groupId);

        return groupAdminMapper.selectByExample(example);
    }
}
