package com.lyx.autoperm.config;

import com.lyx.autoperm.config.filter.TokenAuthenticationFilter;
import com.lyx.autoperm.config.filter.TokenLoginFilter;
import com.lyx.autoperm.config.handler.LogoutSuccessHandler;
import com.lyx.autoperm.config.handler.UnauthorizedEntryPoint;
import com.lyx.autoperm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全框架配置类
 * @author 黎勇炫
 * @date 2022年06月10日 9:36
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 密码加密
     * @return org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置用户登录校验逻辑和加密算法(强散列哈希)
     * @param auth
     * @return void
     * @author 黎勇炫
     * @create 2022/6/10
     * @email 1677685900@qq.com
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                 // 未授权处理
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                // 关闭csrf
                .and().csrf().disable()
                // 需要授权的请求
                .authorizeRequests()
                .antMatchers("/autoperm/user/login").anonymous()
                .anyRequest().authenticated()
                // 退出登录的请求路径和对应的处理器
                .and().logout().logoutUrl("/autoperm/user/logout")
                .addLogoutHandler(new LogoutSuccessHandler(redisTemplate)).and()
                // 登录过滤器
                .addFilter(new TokenLoginFilter(authenticationManager(), redisTemplate))
                // 接口认证过滤器
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), redisTemplate)).httpBasic();
    }

}
