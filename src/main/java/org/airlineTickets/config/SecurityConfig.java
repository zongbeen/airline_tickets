package org.airlineTickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //WebSecurityConfigurerAdapter 를 상속받는 클레스에 선언하면 SpringSecurityFilterChain이 자동으로 포함
public class SecurityConfig extends WebSecurityConfigurerAdapter { //메소드 오버라이딩을 통해 보안 설정을 커스터마이징

    @Override
    protected void configure(HttpSecurity http) throws Exception { //http 요청에 대한 보안 설정(페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 설정)

    }

    @Bean
    public PasswordEncoder passwordEncoder() { //비밀번호 암호화하여 저장(해킹시 노출 방지)화
        return new BCryptPasswordEncoder();
    }
}
