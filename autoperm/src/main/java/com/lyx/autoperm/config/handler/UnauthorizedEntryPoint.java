package com.lyx.autoperm.config.handler;

import com.alibaba.fastjson.JSON;
import com.lyx.autoperm.constant.HttpStatus;
import com.lyx.autoperm.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未授权统一处理
 * @author 黎勇炫
 * @date 2022年06月10日 11:04
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(R.error(HttpStatus.UNAUTHORIZED,"权限不足")));
    }
}
