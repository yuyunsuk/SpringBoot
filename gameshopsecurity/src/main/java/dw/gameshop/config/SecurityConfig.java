package dw.gameshop.config;

import dw.gameshop.exception.MyAccessDeniedHandler;
import dw.gameshop.exception.MyAuthenticationEntryPoint;
import dw.gameshop.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;

// [login.html] form 부분
//          <form action="/login" method="POST">
//            <input type="hidden" th:name="${_csrf?.parameterName}" th:value="${_csrf?.token}" />
//            <div class="mb-3">
//              <label class="form-label text-white">User ID</label>
//              <input type="text" class="form-control" name="username">
//            </div>
//            <div class="mb-3">
//              <label class="form-label text-white">Password</label>
//              <input type="password" class="form-control" name="password">
//            </div>
//            <button type="submit" class="btn btn-primary">Submit</button>
//          </form>

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/user/login"), // Rest API 용 주소, Rest API 로그인, /user/** 이하 모든것, /user/* 첫번째 레벨 자식들
                                new AntPathRequestMatcher("/user/signup"), // Rest API 용 주소, 가입
                                new AntPathRequestMatcher("/login") // 일반주소, Static Page login 등 허용 주소를 기재함
                        ).permitAll() // 모두 허용
                        .anyRequest().authenticated()) // 어떠한 요청이든 인증받겠다.
                .formLogin(form->form.loginPage("/login").defaultSuccessUrl("/articles")) // (formLogin => 로그인 화면 창) 정적 로그인 화면이 존재하는 경우, 로그인 성공시 다음화면 => defaultSuccessUrl
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // 세션 인증시
                .csrf(AbstractHttpConfigurer::disable) // CSRF는 Cross Site Request Forgery(사이트 간 요청 위조) 다른 사이트에서 들어오는 요청 막음 => 안씀
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint()) // 인증예외
                        .accessDeniedHandler(new MyAccessDeniedHandler())) // 인가예외(권한실패)
                .build();
    }

    @Bean
    // 생성자
    public AuthenticationManager authenticationManager(HttpSecurity http,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); // DAO (Database Acess Object)
        authProvider.setUserDetailsService(userDetailService); // 사용할 userDetailService
        authProvider.setPasswordEncoder(bCryptPasswordEncoder); // 사용할 Password Encoder 정보
        return new ProviderManager(authProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { // BCrypt 는 블로피시 암호에 기반을 둔 [암호화 해시 함수]
        return new BCryptPasswordEncoder();
    }
    // 인코딩 => 디코딩이 가능한 암호화 기법
    // 인크립션 => 디코딩이 불가능한 암호화 기법 (해시 암호화)
    
}
