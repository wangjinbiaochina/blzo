package com.jdkhome.blzo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jdk on 17/4/25.
 * 所有的test case 均需继承自该类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest extends AbstractJUnit4SpringContextTests{


    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void initMock(){
        //init mockito annotation
        MockitoAnnotations.initMocks(this);
    }

}
