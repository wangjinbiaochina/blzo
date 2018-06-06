"use strict";

(function ($, request) {

    function AdminList() {
        this.dom = {};
    }

    AdminList.prototype = {
        init: function () {
            var _this = this;

            $('.delete').click(function (event) {
                var adminId = $(this).data('id');
                console.log(adminId);
                request.apiSystemAdminDel($(document), {
                    adminId: adminId
                }, 'adminList');
            });
            $(document).on('adminList', function (msg, data) {
                if (data.code != 200) {
                    new NotificationFx({
                        message : data.msg
                    }).show();
                } else {
                    new NotificationFx({
                        message : data.msg,
                        onClose :function(){location.reload();}
                    }).show();
                }
            });
        }
    };

    window.controller.AdminList = AdminList;

})(jQuery, controller.Request);