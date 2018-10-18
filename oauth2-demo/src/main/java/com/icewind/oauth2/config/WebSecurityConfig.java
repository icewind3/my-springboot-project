package com.icewind.oauth2.config;

import com.icewind.oauth2.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author icewind
 * @date 2018/1/15
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
////                .csrf().disable().httpBasic().disable()
//                .exceptionHandling()
//                .accessDeniedHandler(new SsoAccessDeniedHandler())
//                .authenticationEntryPoint(new SsoAuthenticationEntryPoint())
//                .and().authorizeRequests()
////                    .antMatchers("/oauth/token", "/oauth/check_token").permitAll()
////                    .antMatchers("/", "/home").permitAll()
//                .anyRequest()
//                .authenticated() //任何请求,登录后可以访问
//                .and().formLogin()
//                .loginProcessingUrl("/login").permitAll()
//                .successHandler(new SsoAuthenticationSuccessHandler())
//                .failureHandler(new SsoAuthenticationFailureHandler())
//                .loginPage("/login")
////                .defaultSuccessUrl("/hello")
////                .failureUrl("/login?error")
//                .and().logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(new SsoLogoutSuccessHandler())
//                .invalidateHttpSession(true);
////                .permitAll(); //注销行为任意访问

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .httpBasic().disable();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
