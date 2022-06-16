package com.lyx.autoperm.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyx.autoperm.constant.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 黎勇炫
 * @date 2022年06月10日 11:25
 */
public class ResponseUtils {
    public static void write(HttpServletResponse response,R r) throws IOException {
        ObjectMapper om = new ObjectMapper();
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpStatus.SUCCESS);
        response.setContentType("application/json;charset=utf-8");
        om.writeValue(writer,r);
    }
}
