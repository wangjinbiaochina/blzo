package com.jdkhome.blzo.common.aop.authj;

import com.alibaba.fastjson.JSONObject;
import com.jdkhome.blzo.common.enums.ResponseError;
import com.jdkhome.blzo.common.response.ApiResponse;
import com.jdkhome.blzo.common.tool.IpTools;
import com.jdkhome.blzo.service.basic.LogBasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by jdk.
 */
@Component
public class AuthjInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthjManager authjManager;

    @Autowired
    LogBasicService logBasicService;

    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        Authj authj = null;
        String uri = null;
        if (handler instanceof HandlerMethod) {

            HandlerMethod h = (HandlerMethod) handler;
            authj = h.getMethodAnnotation(Authj.class);
            // 没有注解
            if (authj == null) {
                return true;
            }

            uri = request.getServletPath();
            if (!authjManager.authentication(uri)) {
                // 没有权限

                if (uri.startsWith("/api")) {
                    // api 返回没有权限
                    ApiResponse resp = ApiResponse.responseWithRespError(ResponseError.NO_PERMISSION);
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json");
                    PrintWriter pw = response.getWriter();
                    pw.write(JSONObject.toJSONString(resp));
                    pw.flush();
                } else {
                    // page 跳转到错误页面
                    response.setCharacterEncoding("utf-8");
                    response.sendRedirect("/");
                }
                return false;
            }
        }

        if (authj.auth()) {
            // 鉴权通过 记录日志
            StringBuilder sb = new StringBuilder();
            Enumeration<String> e = request.getParameterNames();
            if (e.hasMoreElements()) {
                while (e.hasMoreElements()) {
                    String name = e.nextElement();
                    String[] values = request.getParameterValues(name);
                    if (values.length == 1) {
                        sb.append(name).append("=").append(values[0]);
                    } else {
                        sb.append(name).append("[]={");
                        for (int i = 0; i < values.length; i++) {
                            if (i > 0) {
                                sb.append(",");
                            }
                            sb.append(values[i]);
                        }
                        sb.append("}");
                    }
                    sb.append("  ");
                }
            }
            logBasicService.addLog(authjManager.getUserId(), authjManager.getUserName(), uri, authj.value(), sb.toString(), IpTools.getIp(request));
        }
        return true;
    }

}
