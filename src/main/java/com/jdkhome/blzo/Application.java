package com.jdkhome.blzo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@EnableDiscoveryClient
//@EnableFeignClients
//springbootApplication <==> @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
@MapperScan("com.jdkhome.blzo.generator.dao")
public class Application implements CommandLineRunner{
    
    //日志
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Springboot应用程序入口
     */
    public static void main(String[] args) {
        ApplicationContext app=SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {}
}
