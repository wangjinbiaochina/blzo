/*
 request.js
 网络请求列表
 */

"use strict";

(function (Base) {

    function Request() {
    }


    // 管理员登录API
    Request.apiLogin = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/login', context, data, event, callbacks);
    };

    //======管理员=======
    // 添加管理员
    Request.apiSystemAdminAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/admin/add', context, data, event, callbacks);
    };

    // 编辑管理员
    Request.apiSystemAdminEdit = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/admin/edit', context, data, event, callbacks);
    };

    // 删除管理员
    Request.apiSystemAdminDel = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/admin/del', context, data, event, callbacks);
    };

    //======菜单=======
    // 添加菜单
    Request.apiSystemMenuAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/menu/add', context, data, event, callbacks);
    };

    // 编辑菜单
    Request.apiSystemMenuEdit = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/menu/edit', context, data, event, callbacks);
    };

    // 删除菜单
    Request.apiSystemMenuDel = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/menu/del', context, data, event, callbacks);
    };

    //======权限=======
    // 添加权限
    Request.apiSystemAuthAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/auth/add', context, data, event, callbacks);
    };

    // 编辑权限
    Request.apiSystemAuthEdit = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/auth/edit', context, data, event, callbacks);
    };

    // 删除权限
    Request.apiSystemAuthDel = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/auth/del', context, data, event, callbacks);
    };

    //======组=======
    // 添加组
    Request.apiSystemGroupAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/add', context, data, event, callbacks);
    };

    // 编辑组
    Request.apiSystemGroupEdit = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/edit', context, data, event, callbacks);
    };

    // 删除组
    Request.apiSystemGroupDel = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/del', context, data, event, callbacks);
    };

    // 添加成员组关联
    Request.apiSystemGroupAdminAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/admin/add', context, data, event, callbacks);
    };

    // 移除成员组关联
    Request.apiSystemGroupAdminRemove = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/admin/remove', context, data, event, callbacks);
    };

    // 添加菜单组关联
    Request.apiSystemGroupMenuAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/menu/add', context, data, event, callbacks);
    };

    // 移除菜单组关联
    Request.apiSystemGroupMenuRemove = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/menu/remove', context, data, event, callbacks);
    };

    // 添加权限组关联
    Request.apiSystemGroupAuthAdd = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/auth/add', context, data, event, callbacks);
    };

    // 移除权限组关联
    Request.apiSystemGroupAuthRemove = function (context, data, event, callbacks) {
        Base.doPost('/api/manager/system/group/auth/remove', context, data, event, callbacks);
    };

    window.controller.Request = Request;

})(controller.Base);

