"use strict";

(function ($, request) {

    function GroupList() {
        this.dom = {};
    }

    GroupList.prototype = {
        init: function () {
            var _this = this;

            $('.delete').click(function (event) {
                var groupId = $(this).data('id');
                console.log(groupId);
                request.apiSystemGroupDel($(document), {
                    groupId: groupId
                }, 'groupDel');
            });
            $(document).on('groupDel', function (msg, data) {
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

    window.controller.GroupList = GroupList;

})(jQuery, controller.Request);