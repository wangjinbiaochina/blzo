package com.jdkhome.blzo.controller.manager.system;

import com.jdkhome.blzo.common.aop.authj.Authj;
import com.jdkhome.blzo.common.aop.log.controller.ControllerLog;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.exception.ServiceException;
import com.jdkhome.blzo.common.response.ApiResponse;
import com.jdkhome.blzo.service.basic.GroupBasicService;
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
@RequestMapping("/api/manager/system/group")
public class GroupApiController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GroupBasicService groupBasicService;

    /**
     * 添加组
     *
     * @param request
     * @param response
     * @return
     */
    @Authj("添加组")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupAdd(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam(value = "name", required = true) String name,
                                                @RequestParam(value = "remark", required = false) String remark
    ) {

        try {

            groupBasicService.addGroup(name, remark);

        } catch (ServiceException se) {
            logger.error("添加组,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("添加组,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 删除组
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("删除组")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupDel(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam(value = "groupId", required = true) Integer groupId
    ) {

        try {

            groupBasicService.delGroup(groupId);

        } catch (ServiceException se) {
            logger.error("删除组,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("删除组,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 编辑组
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("编辑组")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupEdit(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestParam(value = "groupId", required = true) Integer groupId,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "remark", required = false) String remark
    ) {

        try {

            groupBasicService.editGroups(groupId, name, remark);

        } catch (ServiceException se) {
            logger.error("编辑组,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("编辑组,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 添加组成员
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("添加组成员")
    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupAdminAdd(HttpServletRequest request, HttpServletResponse response,
                                                     @RequestParam(value = "groupId", required = true) Integer groupId,
                                                     @RequestParam(value = "adminId", required = true) Integer adminId
    ) {

        try {

            groupBasicService.addGroupAdmin(groupId, adminId);

        } catch (ServiceException se) {
            logger.error("添加组成员,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("添加组成员,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 移除组成员
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("移除组成员")
    @RequestMapping(value = "/admin/remove", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupAdminRemove(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam(value = "groupId", required = true) Integer groupId,
                                                        @RequestParam(value = "adminId", required = true) Integer adminId
    ) {

        try {

            groupBasicService.delGroupAdmin(groupId, adminId);

        } catch (ServiceException se) {
            logger.error("移除组成员,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("移除组成员,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 添加组权限
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("添加组权限")
    @RequestMapping(value = "/auth/add", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupAuthAdd(HttpServletRequest request, HttpServletResponse response,
                                                    @RequestParam(value = "groupId", required = true) Integer groupId,
                                                    @RequestParam(value = "uri", required = true) String uri
    ) {

        try {

            groupBasicService.addGroupAuth(groupId, uri);

        } catch (ServiceException se) {
            logger.error("添加组权限,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("添加组权限,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }


    /**
     * 移除组权限
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("移除组权限")
    @RequestMapping(value = "/auth/remove", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupAuthRemove(HttpServletRequest request, HttpServletResponse response,
                                                    @RequestParam(value = "groupId", required = true) Integer groupId,
                                                    @RequestParam(value = "uri", required = true) String uri
    ) {

        try {

            groupBasicService.delGroupAuth(groupId, uri);

        } catch (ServiceException se) {
            logger.error("移除组权限,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("移除组权限,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

    /**
     * 添加组菜单
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("添加组菜单")
    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupMenuAdd(HttpServletRequest request, HttpServletResponse response,
                                                    @RequestParam(value = "groupId", required = true) Integer groupId,
                                                    @RequestParam(value = "uri", required = true) String uri
    ) {

        try {

            groupBasicService.addGroupMenu(groupId, uri);

        } catch (ServiceException se) {
            logger.error("添加组菜单,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("添加组菜单,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }


    /**
     * 移除组菜单
     *
     * @param request
     * @param response
     * @param groupId
     * @return
     */
    @Authj("移除组菜单")
    @RequestMapping(value = "/menu/remove", method = RequestMethod.POST)
    @ControllerLog
    public ApiResponse apiManagerSystemGroupMenuRemove(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam(value = "groupId", required = true) Integer groupId,
                                                       @RequestParam(value = "uri", required = true) String uri
    ) {

        try {

            groupBasicService.delGroupMenu(groupId, uri);

        } catch (ServiceException se) {
            logger.error("移除组菜单,业务异常: {}", se.getErrorMsg());
            return ApiResponse.responseWithRespError(se.getResponseError());
        } catch (Exception e) {
            logger.error("移除组菜单,未知异常: {}", e);
            return ApiResponse.responseWithRespError(ResponseError.SERVER_ERROR);
        }

        return ApiResponse.successResponse();

    }

}
