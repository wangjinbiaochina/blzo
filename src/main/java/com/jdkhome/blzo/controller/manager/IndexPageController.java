package com.jdkhome.blzo.controller.manager;

import com.jdkhome.blzo.common.aop.authj.AuthjManager;
import com.jdkhome.blzo.common.aop.authj.Authj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by jdk on 17/6/30.
 * 主页控制器
 */
@Controller
public class IndexPageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthjManager authjManager;

    /**
     * 登录页
     */

    @RequestMapping("/login")
    @Authj(value = "登录", auth = false)
    public String login(Model model, HttpServletRequest request) {

        //访问登录页自动退出登录
        authjManager.delGrant();

        return "login";
    }


    /**
     * 主页，，随便什么吧主页 wellcome
     */
    @RequestMapping(value = {"/", "/index"})
    @Authj(value = "主页", auth = false)
    public String index(Model model, HttpServletRequest request) {

        // 如果没登录，就直接跳到登录页
        if (authjManager.getUserId().equals(0)) {
            return "redirect:/login";
        }


        return "basic/blank";
    }

    /**
     * 测试用的空白列表
     */
    @RequestMapping("/basic/list")
    @Authj(value = "测试列表页", menu = true)
    public String list(Model model, HttpServletRequest request) {

        return "basic/list";
    }


}
