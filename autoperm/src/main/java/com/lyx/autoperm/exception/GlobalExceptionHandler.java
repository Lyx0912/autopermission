package com.lyx.autoperm.exception;

import com.lyx.autoperm.constant.HttpStatus;
import com.lyx.autoperm.utils.R;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 黎勇炫
 * @date 2022年06月10日 16:50
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 会员模块异常
     * @param e
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    @ExceptionHandler(UserException.class)
    public R userExceptionHandler(UserException e) {
     return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 其他异常
     * @param e
     * @return com.lyx.autoperm.utils.R
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        return R.error(HttpStatus.EXCEPTION, e.getMessage());
    }
}
