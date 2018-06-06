package com.jdkhome.blzo.common.component.generator;

import java.util.Random;

/**
 * Created by jdk on 17/12/9.
 * 随机数生成器
 */
public class RandomGenarator {

    /**
     * 创建 Long 随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static Long generatorLong(Long min, Long max) {

        return min + ((long) (new Random().nextDouble() * (max - min)));
    }

}
