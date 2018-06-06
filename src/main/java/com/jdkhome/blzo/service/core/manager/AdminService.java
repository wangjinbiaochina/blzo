package com.jdkhome.blzo.service.core.manager;

import com.jdkhome.blzo.common.aop.log.service.ServiceLog;
import com.jdkhome.blzo.generator.model.Admin;

/**
 * Created by jdk on 17/12/6.
 * 管理员业务接口
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @param ip
     * @return
     */
    @ServiceLog("管理员登录")
    Admin login(String username, String password, String ip);
}
