package com.lyx.autoperm.config.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyx.autoperm.constant.HttpStatus;
import com.lyx.autoperm.entity.User;
import com.lyx.autoperm.exception.UserCodeEnum;
import com.lyx.autoperm.exception.UserException;
import com.lyx.autoperm.utils.JwtUtils;
import com.lyx.autoperm.utils.R;
import com.lyx.autoperm.utils.ResponseUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author 黎勇炫
 * @date 2022年06月10日 10:40
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        // 设置登录路径匹配/autoperm/user/login
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/autoperm/user/login","POST"));
    }

    /**
     * 身份验证
     * @param req
     * @param res
     * @return org.springframework.security.core.Authentication
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        User user;
        try {
         user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        } catch (Exception e) {
            throw new UserException(UserCodeEnum.AUTHENTICATION_FAILED);
        }
        // 调用UserDetailsServiceImpl.loadUserByUsername
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));

    }

    /**
     * 登录成功
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException {
        User user = (User) auth.getPrincipal();
        // 生成token
        String token = JwtUtils.getJwtToken(user.getId(),user.getUsername());
        // 将token信息存入redis缓存
        redisTemplate.opsForValue().set(user.getUsername(), user.getPermissions());

        ResponseUtils.write(res,R.ok().setData(token));
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException {

        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(R.error(HttpStatus.UNAUTHENTICATE,"认证失败")));
    }
}