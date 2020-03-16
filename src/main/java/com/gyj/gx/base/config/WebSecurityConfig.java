package com.gyj.gx.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
        http.authorizeRequests()
                .antMatchers("/test/*", "/js/*", "/css/*", "/theme/**", "/laydate/**").permitAll()
                .antMatchers("/book/*", "/book").permitAll()
                .anyRequest().authenticated();

        // 关闭CSRF，防跨域以放行POST等请求
        http.csrf().disable();
    }

    @Autowired
    AUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}