"use strict";

(function ($, request) {

    function GroupAdd() {
        this.dom = {};
    }

    GroupAdd.prototype = {
        init: function () {
            var _this = this;

            $('#save').click(function (event) {


                var obj = {
                    "name": $('#name').val(),
                    "remark": $('#remark').val()
                };

                console.log(obj);

                request.apiSystemGroupAdd($(document), obj, 'groupAdd');
            });

            $(document).on('groupAdd', function (msg, data) {
                if (data.code != 200) {
                    new NotificationFx({
                        message: data.msg
                    }).show();
                } else {
                    new NotificationFx({
                        message: data.msg,
                        onClose: function () {
                            location.reload();
                        }
                    }).show();
                }
            });
        },
        select: function (id) {
            var num = 0;
            $("#" + id + " option").map(function (index, item) {
                if ($(item).html() == $('#' + id + '_chosen .chosen-single span').html()) {
                    num = index;
                }
            });
            var value = $("#" + id + " option").eq(num).val();
            return value;
        }
    };

    window.controller.GroupAdd = GroupAdd;

})(jQuery, controller.Request);