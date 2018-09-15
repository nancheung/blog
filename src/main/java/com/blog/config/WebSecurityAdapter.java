package com.blog.config;

import com.blog.auth.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security权限校验
 *
 * @author NanCheung
 */
@Configuration
@EnableWebSecurity
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用CSRF跨域
        http.csrf().disable();
        //配置无条件允许访问路径
        http.authorizeRequests().antMatchers("/", "/blog/**", "/tag/**", "friend", "/login/**").permitAll()
                //配置登陆用户允许访问路径
                .antMatchers("/admin/**").authenticated()
//                .and().rememberMe().tokenValiditySeconds(3600)
                //配置登陆页面和登陆后默认页面
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/admin/article/list").permitAll()
                //配置登出页面
                .and().logout().logoutUrl("/admin/loginOut").permitAll();
        
    }
    
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**/*.*");
    }
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
}
