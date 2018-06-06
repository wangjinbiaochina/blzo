"use strict";

(function ($, request) {

    function Login() {
        this.dom = {};
    }

    Login.prototype = {
        init: function () {


            $("#login").click(function (event) {

                request.apiLogin($(document), {
                    username: $('input[name="username"]').val(),
                    password: $('input[name="password"]').val()
                }, 'apiLogin');

                event.preventDefault();
            });
            $(document).on('apiLogin', function (msg, data) {
                if (data.code != 200) {
                    new NotificationFx({
                        message : data.msg
                    }).show();
                } else {
                    new NotificationFx({
                        message : data.msg,
                        onClose :function(){window.location.href = "/index";}
                    }).show();
                }
            });

        }
    };

    window.controller.Login = Login;

})(jQuery, controller.Request);