package com.xxxx.server.config.security;

import com.xxxx.server.config.security.component.*;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @date ：Created in 2021/2/4 18:03
 * @description：Security配置了
 * @author：
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;


    // (第2步).
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
         auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html"
                ,"favicon.ico"
                ,"/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha"
        );
    }



    // (第3步).
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  使用JWT,不需要csrf
        http.csrf()
                .disable()
                // 基于token ， 不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                   // 允许登录访问
                   .antMatchers("/login","/logout")
                   .permitAll()
                // 所有请求要求认证
                .anyRequest()
                .authenticated()
                // 动态权限配置
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(customUrlDecisionManager);
                        object.setSecurityMetadataSource(customFilter);
                        return object;
                    }
                })
                .and()
                // 禁用缓存
                .headers()
                .cacheControl();
        // 添加过滤器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //  添加自定义未授权和未登录返回结果
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    // (第1步). 登录方法
    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        //
        return username ->{
            Admin admin = adminService.getAdminByUserName(username);
            // 用户登录以后根据用户id获取角色
            if (null!=admin){
                // 根据用户id设置用户角色 (用户登录以后根据用户id获取角色)
                admin.setRoles(adminService.getRoles(admin.getId()));
                return admin;
            }
            //
            throw new UsernameNotFoundException("用户名或密码不正确");
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        // return new BCryptPasswordEncoder类的构造方法创建 passwordEncoder()方法
        return new BCryptPasswordEncoder();

        /*
           这样写跟上面一样效果吗?
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        return  passwordEncoder;*/
    }
    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        // return new JwtAuthencationTokenFilter类的构造方法创建 jwtAuthencationTokenFilter()方法
        return new JwtAuthencationTokenFilter();
    }
}
