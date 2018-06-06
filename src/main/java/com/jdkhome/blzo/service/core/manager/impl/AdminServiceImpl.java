package com.jdkhome.blzo.service.core.manager.impl;

import com.jdkhome.blzo.common.component.coder.PasswordEncoder;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.generator.dao.AdminMapper;
import com.jdkhome.blzo.generator.model.Admin;
import com.jdkhome.blzo.service.basic.AdminBasicService;
import com.jdkhome.blzo.service.core.manager.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jdk on 17/12/6.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    AdminBasicService adminBasicService;

    @Autowired
    AdminMapper adminMapper;

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @param ip
     * @return
     */
    @Override
    public Admin login(String username, String password, String ip) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(ip)) {
            logger.error("管理员登录->登录必要参数为空");
            throw new ServiceException(ResponseError.PARAMETER_ERROR);
        }

        // 获取对应管理员
        Admin admin = adminBasicService.getAdminByUsername(username);
        if (admin == null) {
            logger.error("管理员登录->管理员不存在");
            throw new ServiceException(ResponseError.RESP_ERROR_ADMIN_NOT_EXIST);
        }

        //密码验证
        if (!admin.getPassword().equals(PasswordEncoder.toMD5(password, admin.getSalt()))) {
            logger.error("管理员登录->密码错误");
            throw new ServiceException(ResponseError.RESP_ERROR_PASSWORD_ERROR);
        }

        //更新ip
        admin.setLastIp(ip);
        admin.setLastTime(new Date());

        adminMapper.updateByPrimaryKeySelective(admin);

        return admin;
    }
}
