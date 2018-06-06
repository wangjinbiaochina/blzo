package com.jdkhome.blzo.controller;

import com.jdkhome.blzo.BaseTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by jdk on 17/5/17.
 * 控制器接口测试基类，所有的@Controller测试类继承自该类
 */
public class BaseControllerTest extends BaseTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        //初始化mockMvc，用于模拟接口请求
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

}
