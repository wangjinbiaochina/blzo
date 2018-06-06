package com.jdkhome.blzo.controller.manager.system;

import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.aop.authj.Authj;
import com.jdkhome.blzo.generator.model.Admin;
import com.jdkhome.blzo.pojo.dto.request.PageRequest;
import com.jdkhome.blzo.service.basic.AdminBasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jdk on 17/11/28.
 * 后台菜单管理
 * 管理员
 */
@Controller
@RequestMapping("/system/admin")
public class AdminPageController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminBasicService adminBasicService;

    /**
     * 管理员列表页
     */
    @RequestMapping("/list")
    @Authj(value = "管理员列表页",menu = true)
    public String adminList(Model model, HttpServletRequest request,
                            PageRequest pageRequest,
                            @RequestParam(value = "userName", required = false) String userName,
                            @RequestParam(value = "nickName", required = false) String nickName,
                            @RequestParam(value = "phone", required = false) String phone
    ) {

        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }

        //获取管理员列表
        PageInfo pageInfo = adminBasicService.getAdminsWithPage(userName, nickName, phone, pageRequest.getPage(), pageRequest.getSize());
        model.addAttribute("pageInfo", pageInfo);

        return "system/admin/list";
    }

    /**
     * 管理员编辑页
     */
    @RequestMapping("/edit")
    @Authj("管理员编辑页")
    public String adminEdit(Model model, HttpServletRequest request,
                            @RequestParam(value = "adminId", required = true) Integer adminId) {

        Admin obj = adminBasicService.getAdminById(adminId);

        model.addAttribute("obj", obj);

        return "system/admin/edit";
    }

    /**
     * 管理员添加页
     */
    @Authj("管理员添加页")
    @RequestMapping("/add")
    public String adminAdd(Model model, HttpServletRequest request) {

        return "system/admin/add";
    }

}
