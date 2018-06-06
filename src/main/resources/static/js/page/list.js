"use strict";

(function ($, request, s) {

    function List() {
        this.dom = {};
    }

    List.prototype = {
        init: function () {
            var _this = this;
            if (s != undefined){
                s.init();
            }
        }
    };

    window.controller.List = List;

})(jQuery, controller.Request, controller.Search);