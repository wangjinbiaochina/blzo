package com.jdkhome.blzo.controller.manager;

import com.jdkhome.blzo.common.aop.authj.AuthjBean;
import com.jdkhome.blzo.common.aop.authj.AuthjCache;
import com.jdkhome.blzo.common.aop.authj.AuthjManager;
import com.jdkhome.blzo.common.aop.authj.UserAuthjConfBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jdk on 17/8/17.
 * 全局公共ControllerAdvice
 */
@ControllerAdvice("com.jdkhome.blzo.controller.manager")
public class CommonControllerAdvice {

    @Autowired
    AuthjManager authjManager;

    @Autowired
    AuthjCache authjCache;

    /**
     * 给model添加初始值
     *
     * @param model 页面model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        model.addAttribute("projectName", "BLZO");

        AuthjBean authjBean = authjCache.getAuthj(request.getServletPath());

        String pageName = "未命名";
        if (authjBean != null) {
            pageName = authjBean.getName();
        }
        model.addAttribute("pageName", pageName);

        String nickName = "未登录";
        UserAuthjConfBean userAuthjConfBean = authjManager.getUserAuthjConfBean();
        if (userAuthjConfBean != null) {
            model.addAttribute("groupMenus", userAuthjConfBean.getMenuList());
            nickName = userAuthjConfBean.getName();
        }
        model.addAttribute("nickName", nickName);

    }
}
