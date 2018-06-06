package com.jdkhome.blzo.common.constant;

/**
 * Created by lee on 17/5/17.
 * redis缓存的key常量
 *
 * @CreatedBy lee
 * @Date 17/5/17
 */
public final class RedisKeyCons {

    //三要素验证失败次数
    public static final String SURVEY_APPLY_VALIDATE_FAILURE_TIMES = "surveyApplyValidateFailureTimes";

    //验证码
    public static final String CAPTCHA = "captcha";

    //实名认证
    public static final String USER_AUTH_ERROR_TIMES = "user_auth_error_times";

    //更新订单后需要发送消息的消息通道
    public static final String ORDER_UPDATE_MSG_CHANNEL = "order_update_msg_channel";

    //聊天
    public static final String CHAT = "chat";

    //token
    public static final String TOKEN = "token";

    private RedisKeyCons() {
    }
}
