package com.jdkhome.blzo.service.basic.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.constant.SqlTemplate;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.generator.dao.LogMapper;
import com.jdkhome.blzo.generator.model.Admin;
import com.jdkhome.blzo.generator.model.Log;
import com.jdkhome.blzo.generator.model.LogExample;
import com.jdkhome.blzo.service.basic.AdminBasicService;
import com.jdkhome.blzo.service.basic.LogBasicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author linkji.
 */
@Service
public class LogBasicServiceImpl implements LogBasicService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogMapper logMapper;

    @Autowired
    AdminBasicService adminBasicService;

    /**
     * 添加日志
     *
     * @param adminId   管理员Id
     * @param authjUri  authj地址
     * @param authjName authjName
     * @param paramers  参数(json)
     * @param ip        ip
     * @return
     */
    @Override
    public Integer addLog(Integer adminId, String adminName, String authjUri, String authjName, String paramers, String ip) {

        if (adminId == null || StringUtils.isEmpty(adminName) || StringUtils.isEmpty(authjUri) || StringUtils.isEmpty(authjName)) {
            logger.error("添加日志 -> 参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        Log log = new Log();
        log.setAdminId(adminId);
        log.setAdminName(adminName);
        log.setAuthjUri(authjUri);
        log.setAuthjName(authjName);
        log.setParamers(paramers);
        log.setIp(ip);

        logMapper.insertSelective(log);

        return 0;
    }

    /**
     * 获取日志通过Id
     *
     * @param logId
     * @return
     */
    @Override
    public Log getLog(Integer logId) {

        if (logId == null) {
            logger.error("获取日志通过Id -> 参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        Log log = logMapper.selectByPrimaryKey(logId);
        return log;
    }

    private LogExample getExample(String nickName, String authjUri, String authjName) {

        LogExample example = new LogExample();
        LogExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(nickName)) {
            List<Admin> adminList = adminBasicService.getAllAdmin(null, nickName, null);
            List<Integer> adminIds = new ArrayList<>();
            adminIds.add(-1);
            if (adminList != null && !adminList.isEmpty()) {
                adminList.forEach(admin -> adminIds.add(admin.getId()));
            }

            criteria.andAdminIdIn(adminIds);
        }

        if (StringUtils.isNotEmpty(authjUri)) {
            criteria.andAuthjUriLike("%" + authjUri + "%");
        }

        if (StringUtils.isNotEmpty(authjName)) {
            criteria.andAuthjNameLike("%" + authjName + "%");
        }

        example.setOrderByClause(SqlTemplate.ORDER_BY_ID_DESC);

        return example;

    }

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
    @Override
    public PageInfo<Log> getLogWithPage(String nickName, String authjUri, String authjName, Integer page, Integer size) {

        if (page == null || size == null) {
            logger.error("分页查询日志 -> 参数错误");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        LogExample example = this.getExample(nickName, authjUri, authjName);

        PageHelper.startPage(page, size);
        return new PageInfo<>(logMapper.selectByExampleWithBLOBs(example));
    }
}
