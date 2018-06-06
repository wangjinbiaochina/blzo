"use strict";

(function ($, request) {

    function GroupMenu() {
        this.dom = {};
    }

    GroupMenu.prototype = {
        init: function () {
            var _this = this;

            $('.add').click(function (event) {
                request.apiSystemGroupMenuAdd($(document), {
                    groupId: $("#groupId").val(),
                    uri: $(this).data('uri')
                }, 'groupMenuAdd');

            });
            $('.remove').click(function (event) {

                request.apiSystemGroupMenuRemove($(document), {
                    groupId: $("#groupId").val(),
                    uri: $(this).data('uri')
                }, 'groupMenuRemove');
            });


            $(document).on('groupMenuAdd', function (msg, data) {
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
            $(document).on('groupMenuRemove', function (msg, data) {
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

    window.controller.GroupMenu = GroupMenu;

})(jQuery, controller.Request);