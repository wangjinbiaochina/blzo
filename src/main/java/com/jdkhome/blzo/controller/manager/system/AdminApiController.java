package com.jdkhome.blzo.controller.manager.system;

import com.jdkhome.blzo.common.aop.authj.Authj;
import com.jdkhome.blzo.common.aop.log.controller.ControllerLog;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.common.response.ApiResponse;
import com.jdkhome.blzo.service.basic.AdminBasicService;
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
 * Created by jdk on 17/12/5.
 */
@RestController
@RequestMapping("/api/manager/system/admin")
public class AdminApiController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminBasicService adminBasicService;

    /**
     * 添加管理员
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ControllerLog
    @Authj("添加管理员")
    public ApiResponse apiManagerSystemAdminAdd(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam(value = "username", required = true) String username,
                                                @RequestParam(value = "password", required = true) String password,
                                                @RequestParam(value = "nickName", required = true) String nickName,
                                                @RequestParam(value = "phone", required = true) String phone,
                                                @RequestParam(value = "remark", required = false) String remark
    ) {

        try {

            adminBasicService.addAdmin(username, password, nickName, phone, remark);

        } catch (ServiceException se) {
            logger.error("添加管理员,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("添加管理员,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 删除管理员
     *
     * @param request
     * @param response
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ControllerLog
    @Authj("删除管理员")
    public ApiResponse apiManagerSystemAdminDel(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam(value = "adminId", required = true) Integer adminId
    ) {

        try {

            adminBasicService.delAdmin(adminId);

        } catch (ServiceException se) {
            logger.error("删除管理员,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("删除管理员,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 编辑管理员
     *
     * @param request
     * @param response
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ControllerLog
    @Authj("编辑管理员")
    public ApiResponse apiManagerSystemAdminEdit(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestParam(value = "adminId", required = true) Integer adminId,
                                                 @RequestParam(value = "username", required = false) String username,
                                                 @RequestParam(value = "password", required = false) String password,
                                                 @RequestParam(value = "nickName", required = false) String nickName,
                                                 @RequestParam(value = "phone", required = false) String phone,
                                                 @RequestParam(value = "remark", required = false) String remark
    ) {

        try {

            adminBasicService.editAdmin(adminId, username, password, nickName, phone, remark);

        } catch (ServiceException se) {
            logger.error("编辑管理员,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("编辑管理员,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

}
