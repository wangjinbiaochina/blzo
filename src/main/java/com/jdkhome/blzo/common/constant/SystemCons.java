package com.jdkhome.blzo.common.constant;

/**
 * Created by lee on 17/5/25.
 * 系统相关常量
 *
 * @CreatedBy lee
 * @Date 17/5/25
 */
public final class SystemCons {

    //缓存登录态的键
    public static final String USER_ID = "user_id";

    //token
    public static final String TOKEN = "token";

    //没有卡图片的URL地址
    public static final String NONE_CARD_URL = "http://jinniubao.oss-cn-shenzhen.aliyuncs.com/userAvatar/defaultAvatar.png";


    /**
     * http
     */
    public static final String HTTP = "http://";


    /**
     * 下一天的界限
     */
    public static final Integer nextDayHour = 21;

    /**
     * 单笔金额最大值
     */
    public static final Long maxAmount = 499999L;

    /**
     * 随机金额下限
     */
    public static final Long minRandomAmount = 300000L;

    /**
     * 随机金额上限
     */
    public static final Long maxRandomAmount = 450000L;
    //管理费

    private SystemCons() {
    }
}
