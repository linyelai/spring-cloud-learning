package com.mujiejun.config;

import com.mujiejun.filter.MyAccessDecisionManager;
import com.mujiejun.filter.MyAccessDeniedHandler;
import com.mujiejun.filter.MyFilterInvocationSecurityMetadataSource;
import com.mujiejun.filter.TokenAuthenticationFilter;
import com.mujiejun.service.member.UserDetailService;
import com.mujiejun.validate.smscode.SmsAuthenticationConfig;
import com.mujiejun.validate.smscode.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;  //实现了UserDetailsService接口
    @Autowired
    private MyFilterInvocationSecurityMetadataSource filterMetadataSource; //权限过滤器（当前url所需要的访问权限）
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;//权限决策器
    @Autowired
    private MyAccessDeniedHandler deniedHandler;//自定义错误(403)返回数据

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/code/sms").permitAll()
                    .antMatchers("/code/image").permitAll()
                    .and().addFilterAfter(tokenAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                            o.setSecurityMetadataSource(filterMetadataSource);
                            o.setAccessDecisionManager(myAccessDecisionManager);
                            return o;
                        }
                    })
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler);



    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    //@Autowired
    private SmsCodeFilter smsCodeFilter;

    //@Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
