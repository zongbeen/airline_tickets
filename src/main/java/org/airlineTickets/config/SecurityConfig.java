package org.airlineTickets.config;

import org.airlineTickets.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //WebSecurityConfigurerAdapter 를 상속받는 클레스에 선언하면 SpringSecurityFilterChain이 자동으로 포함
public class SecurityConfig extends WebSecurityConfigurerAdapter { //메소드 오버라이딩을 통해 보안 설정을 커스터마이징

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception { //http 요청에 대한 보안 설정(페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 설정)
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
                ;
        http.authorizeRequests() //시큐리티 처리
                .mvcMatchers("/", "", "", "").permitAll() //모든 사용자가 인증없이 해당 경로 접근 설정
                .mvcMatchers("").hasRole("ADMIN") //ADMIN 계정만 접근 가능
                .anyRequest().authenticated() //나머지 경로 모두 인증 요구
                ;
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //인증되지 않은 사용자가 리소스에 접근하였을 때 수행되는 핸들러를 등록
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("","",""); //static 디렉터리의 하위 파일은 인증을 무시
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //비밀번호 암호화하여 저장(해킹시 노출 방지)화
        return new BCryptPasswordEncoder();
    }
}
