package com.gyj.gx.base.config.security;

import com.gyj.gx.base.config.security.AjaxAuthenticationEntryPoint;
import com.gyj.gx.base.config.security.MyAuthenticationProvider;
import com.gyj.gx.base.config.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    private MyAuthenticationProvider authenticationProvider;

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/pages/login.html").permitAll()
//                .loginProcessingUrl("/login")
//                .successHandler(new MyAuthenticationSuccessHandler())
//                /*.defaultSuccessUrl("/pages/index.html")*/
//                .and()
//                .authorizeRequests()
//                .antMatchers("/","/register","/getCheckCode","/validateCheckCode","/resetPassword","/login").permitAll()
//                .antMatchers("/user","/user/*","/upload","/changePassword","/logout").authenticated()
//                .antMatchers("/user/search/*").hasAuthority("ADMIN")
//                .antMatchers("/statistics/*").permitAll()
//                .antMatchers("/pages/query.html","/pages/item.html").hasAuthority("ADMIN")
//                .antMatchers("/pages/mySpace.html").authenticated()
//                .antMatchers("/pages/*","/css/*","/js/*","/js/echarts/*","/pics/*").permitAll()
//                .anyRequest().authenticated();
//                http.exceptionHandling().accessDeniedPage("/pages/error.html");
//        http.csrf()
//                .ignoringAntMatchers("/user/*","/user","/register","/validateCheckCode","/login", "/upload","/save/*",
//                        "/changePassword","/logout","/user/search/*","/resetPassword","/validateCheckCode");
        // 记得在这里加上需要认证的URL
        // TODO 异常处理、登出、基于TOKEN+REDIS而不是JSESSION
        http
                .httpBasic()
                .authenticationEntryPoint(ajaxAuthenticationEntryPoint)
                .and()
                .formLogin()
                .loginPage("/admin/login.html").permitAll()
                .loginProcessingUrl("/user/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/index.html","/admin/all.html").authenticated()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/category/*").authenticated()
                .anyRequest().authenticated();

        // 关闭CSRF，防跨域以放行POST等请求
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}