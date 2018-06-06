package com.jdkhome.blzo.controller.manager;

import com.jdkhome.blzo.common.aop.authj.AuthjManager;
import com.jdkhome.blzo.common.aop.log.controller.ControllerLog;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.common.response.ApiResponse;
import com.jdkhome.blzo.common.tool.IpTools;
import com.jdkhome.blzo.generator.model.Admin;
import com.jdkhome.blzo.service.core.manager.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jdk on 17/12/6.
 */
@RestController
public class IndexApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminService adminService;

    @Autowired
    AuthjManager authjManager;

    /**
     * 登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/api/manager/login", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemAuthAdd(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "username", required = true) String username,
                                               @RequestParam(value = "password", required = true) String password
    ) {

        try {

            //登录鉴权
            Admin admin = adminService.login(username, password, IpTools.getIp(request));

            //登录授权
            authjManager.grant(admin.getId());

        } catch (ServiceException se) {
            logger.error("登录,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("登录,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

}
