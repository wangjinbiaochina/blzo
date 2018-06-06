package com.jdkhome.blzo.common.aop.authj;

import com.jdkhome.blzo.generator.model.*;
import com.jdkhome.blzo.service.basic.AdminBasicService;
import com.jdkhome.blzo.service.basic.GroupBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jdk on 18/1/6.
 * 权限业务
 */
@Component
public class AuthjService {

    @Autowired
    AdminBasicService adminBasicService;

    @Autowired
    GroupBasicService groupBasicService;

    @Autowired
    AuthjCache authjCache;


    public UserAuthjConfBean getUserAuthjConf(Integer userId) {

        UserAuthjConfBean userAuthjConfBean = new UserAuthjConfBean();

        Admin admin = adminBasicService.getAdminById(userId);

        userAuthjConfBean.setId(admin.getId());
        userAuthjConfBean.setName(admin.getNickName());

        //得到用户组
        List<GroupAdmin> groupAdminList = groupBasicService.getGroupAdminByAdminId(userId);

        Set<String> authUriSet = new HashSet<>();
        List<MenuVO> menuList = new ArrayList<>();

        groupAdminList.stream().forEach(groupAdmin -> {
            Integer groupId = groupAdmin.getGroupId();
            List<GroupAuth> groupAuthList = groupBasicService.getGroupAuthByGroupId(groupId);
            groupAuthList.stream().forEach(groupAuth -> authUriSet.add(groupAuth.getUri()));

            List<GroupMenu> groupMenuList = groupBasicService.getGroupMenuByGroupId(groupId);
            List<AuthjBean> authjBeanList = new ArrayList<>(groupMenuList.size());
            groupMenuList.stream().forEach(groupMenu -> {
                AuthjBean authjBean = authjCache.getAuthj(groupMenu.getUri());
                if (authjBean != null) {
                    authjBeanList.add(authjCache.getAuthj(groupMenu.getUri()));
                }
            });

            Group group = groupBasicService.getGroupById(groupId);

            MenuVO menuVO = new MenuVO();
            menuVO.setGroupName(group.getName());
            menuVO.setAuthjBeans(authjBeanList);
            menuList.add(menuVO);
        });

        userAuthjConfBean.setAuthUriSet(authUriSet);
        userAuthjConfBean.setMenuList(menuList);

        return userAuthjConfBean;
    }
}
