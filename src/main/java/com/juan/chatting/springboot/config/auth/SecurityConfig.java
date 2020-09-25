package com.juan.chatting.springboot.config.auth;

import com.juan.chatting.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화 시켜줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserTypesOAuth2UserService customUserTypesOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**",
                            "/js/**", "/h2-console/**").hasRole(Role.USER.name()) // 권한 관리 대상을 지정하는 옵션
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customUserTypesOAuth2UserService);
    }
}
