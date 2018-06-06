package com.jdkhome.blzo.controller.manager.system;

import com.github.pagehelper.PageInfo;
import com.jdkhome.blzo.common.aop.authj.Authj;
import com.jdkhome.blzo.common.aop.authj.AuthjBean;
import com.jdkhome.blzo.common.aop.authj.AuthjCache;
import com.jdkhome.blzo.generator.model.Admin;
import com.jdkhome.blzo.generator.model.Group;
import com.jdkhome.blzo.generator.model.GroupAuth;
import com.jdkhome.blzo.generator.model.GroupMenu;
import com.jdkhome.blzo.pojo.dto.request.PageRequest;
import com.jdkhome.blzo.pojo.vo.manager.group.GroupAdminVO;
import com.jdkhome.blzo.pojo.vo.manager.group.GroupAuthVO;
import com.jdkhome.blzo.pojo.vo.manager.group.GroupMenuVO;
import com.jdkhome.blzo.service.basic.AdminBasicService;
import com.jdkhome.blzo.service.basic.GroupBasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jdk on 17/11/28.
 * 后台菜单管理
 * 管理员
 */
@Controller
@RequestMapping("/system/group")
public class GroupPageController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    GroupBasicService groupBasicService;

    @Autowired
    AdminBasicService adminBasicService;

    @Autowired
    AuthjCache authjCache;


    /**
     * 组列表页
     */
    @Authj(value = "组列表页", menu = true)
    @RequestMapping("/list")
    public String groupList(Model model, HttpServletRequest request,
                            PageRequest pageRequest,
                            @RequestParam(value = "name", required = false) String name
    ) {

        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }

        PageInfo pageInfo = groupBasicService.getGroupsWithPage(name, pageRequest.getPage(), pageRequest.getSize());

        model.addAttribute("pageInfo", pageInfo);

        return "system/group/list";
    }

    /**
     * 组编辑页
     */
    @Authj("组编辑页")
    @RequestMapping("/edit")
    public String groupEdit(Model model, HttpServletRequest request,
                            @RequestParam(value = "groupId", required = true) Integer groupId) {

        Group obj = groupBasicService.getGroupById(groupId);

        model.addAttribute("obj", obj);

        return "system/group/edit";
    }

    /**
     * 组添加页
     */
    @Authj("组添加页")
    @RequestMapping("/add")
    public String groupAdd(Model model, HttpServletRequest request) {

        return "system/group/add";
    }

    /**
     * 组成员页
     */
    @Authj("组成员页")
    @RequestMapping("/admin")
    public String groupAdmin(Model model, HttpServletRequest request,
                             PageRequest pageRequest,
                             @RequestParam(value = "groupId", required = true) Integer groupId) {

        Group group = groupBasicService.getGroupById(groupId);

        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }

        //组信息
        model.addAttribute("group", group);

        //获取所有管理员
        PageInfo pageInfo = adminBasicService.getAdminsWithPage(null, null, null, pageRequest.getPage(), pageRequest.getSize());

        //获取组内所有管理员Id

        Set<Integer> adminSet = new HashSet<>();
        groupBasicService.getGroupAdminByGroupId(groupId).stream().forEach(groupAdmin -> adminSet.add(groupAdmin.getAdminId()));

        List<GroupAdminVO> list = new ArrayList<>(pageInfo.getSize());
        List<Admin> adminList = pageInfo.getList();
        adminList.stream().forEach(admin -> {
            GroupAdminVO groupAdminVO = new GroupAdminVO();
            BeanUtils.copyProperties(admin, groupAdminVO);

            //查看是否有关联
            groupAdminVO.setHave(adminSet.contains(admin.getId()));
            list.add(groupAdminVO);
        });
        pageInfo.setList(list);

        //组信息
        model.addAttribute("pageInfo", pageInfo);

        return "system/group/admin";
    }

    /**
     * 组权限页
     */
    @Authj("组权限页")
    @RequestMapping("/auth")
    public String groupAuth(Model model, HttpServletRequest request,
                            PageRequest pageRequest,
                            @RequestParam(value = "groupId", required = true) Integer groupId) {

        Group group = groupBasicService.getGroupById(groupId);

        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }

        // 组拥有的权限列表
        List<GroupAuth> groupAuths = groupBasicService.getGroupAuthByGroupId(groupId);

        // 所有authjBeans都是可选权限
        List<AuthjBean> authjBeans = authjCache.getAuthjBeans().stream().filter(authjBean -> authjBean.getAuth()).collect(Collectors.toList());

        // 这是组拥有的权限的uri的集合
        Set<String> uris = new HashSet<>(groupAuths.size());

        List<GroupAuthVO> groupAuthVOList = new ArrayList<>(authjBeans.size());

        groupAuths.stream().forEachOrdered(groupAuth -> {
            GroupAuthVO groupAuthVO = new GroupAuthVO();
            groupAuthVO.setUri(groupAuth.getUri());
            groupAuthVO.setHave(true);
            groupAuthVO.setAuthjBean(authjCache.getAuthj(groupAuth.getUri()));
            groupAuthVOList.add(groupAuthVO);

            uris.add(groupAuth.getUri());
        });

        authjBeans.stream().filter(authjBean -> !uris.contains(authjBean.getUri())).forEachOrdered(authjBean -> {
            GroupAuthVO groupAuthVO = new GroupAuthVO();
            groupAuthVO.setUri(authjBean.getUri());
            groupAuthVO.setHave(false);
            groupAuthVO.setAuthjBean(authjBean);
            groupAuthVOList.add(groupAuthVO);
        });

        //组信息
        model.addAttribute("group", group);
        // 列表
        model.addAttribute("groupAuthVOList", groupAuthVOList);

        return "system/group/auth";
    }

    /**
     * 组菜单页
     */
    @Authj("组菜单页")
    @RequestMapping("/menu")
    public String groupMenu(Model model, HttpServletRequest request,
                            PageRequest pageRequest,
                            @RequestParam(value = "groupId", required = true) Integer groupId) {

        Group group = groupBasicService.getGroupById(groupId);

        if (pageRequest == null) {
            pageRequest = new PageRequest();
        }

        // 组拥有的菜单列表
        List<GroupMenu> groupMenus = groupBasicService.getGroupMenuByGroupId(groupId);

        // 所有authjBeans都是可选权限
        List<AuthjBean> authjBeans = authjCache.getAuthjBeans().stream().filter(authjBean -> authjBean.getMenu()).collect(Collectors.toList());

        // 这是组拥有的菜单的uri的集合
        Set<String> uris = new HashSet<>(groupMenus.size());

        // 构造MenuVO
        List<GroupMenuVO> groupMenuVOList = new ArrayList<>(authjBeans.size());

        groupMenus.stream().forEachOrdered(groupMenu -> {
            GroupMenuVO groupMenuVO = new GroupMenuVO();
            groupMenuVO.setUri(groupMenu.getUri());
            groupMenuVO.setHave(true);
            groupMenuVO.setAuthjBean(authjCache.getAuthj(groupMenu.getUri()));
            groupMenuVOList.add(groupMenuVO);

            uris.add(groupMenu.getUri());
        });

        authjBeans.stream().filter(authjBean -> !uris.contains(authjBean.getUri())).forEachOrdered(authjBean -> {
            GroupMenuVO groupMenuVO = new GroupMenuVO();
            groupMenuVO.setUri(authjBean.getUri());
            groupMenuVO.setHave(false);
            groupMenuVO.setAuthjBean(authjBean);
            groupMenuVOList.add(groupMenuVO);
        });


        // 组信息
        model.addAttribute("group", group);

        // 列表
        model.addAttribute("groupMenuVOList", groupMenuVOList);

        return "system/group/menu";
    }


}
