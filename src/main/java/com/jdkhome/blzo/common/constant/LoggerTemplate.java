package com.jdkhome.blzo.common.constant;

/**
 * Created by lee on 17/8/19.
 * 日志打印模板
 *
 * @CreatedBy lee
 * @Date 17/8/19
 */
public final class LoggerTemplate {


    public static final String GET_SURVEY_RESULT_FAILURE = "获取背调结果失败：{}";

    public static final String UPLOAD_SURVEY_CANDIDATE_FAILURE = "背调人上传失败:{}";

    public static final String REGISTER_FAULURE = "注册失败：{}";

    public static final String LOGIN_FAULURE = "登录失败：{}";

    public static final String UPDATE_USER_INFO_FAULURE = "补充用户资料失败:{}";

    public static final String ORDER_NOT_EXIST = "订单: {}不存在";

    public static final String GET_SURVEY_CANDIDATE_INFO_FAILURE = "查找背调人信息失败: {}";

    public static final String GET_SURVEY_RECORD_BY_RESUSE = "复用背调结果: {}";

    public static final String TABLE_HEAD_WRONG = " 表头不正确，请重新上传:{}";

    public static final String KAOLA_RESPONSE_INFO = " 考拉响应码: {}, 考拉响应信息: {}";

    private LoggerTemplate() {
    }
}
