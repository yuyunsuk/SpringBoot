package dw.gameshop.controller;

import dw.gameshop.dto.SessionDto;
import dw.gameshop.dto.UserDto;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.service.UserDetailService;
import dw.gameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Rest API 사용
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserDetailService userDetailService;
    private AuthenticationManager authenticationManager;
    private HttpServletRequest httpServletRequest;

    public UserController(UserService userService, UserDetailService userDetailService, AuthenticationManager authenticationManager, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("signup") // "/user/signup"
    public ResponseEntity<String> signUp(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), // 저장 Service 로 보냄
                HttpStatus.CREATED); // CREATED => 201 리턴
    }

    @PostMapping("login") // "/user/login" => @PostMapping("login") 에서 "/"를 붙이면 안됨
    public ResponseEntity<String> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate( // 보안 관련 코드이므로 바로 리턴
                new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getPassword()) // 토큰을 만듦 (스프링 시큐리티 인증방식)
        );

        // 인증정보 저장공간
        SecurityContextHolder.getContext().setAuthentication(authentication); // 보안컨텍스트홀더 인증 저장 (컨텍스트에 인증정보 저장)

        // 이후 Sesstion 과 토큰 방식이 틀림, 토큰 방식은 토큰 생성

        // current 테스트를 위해 추가
        // 세션 생성
        HttpSession session = request.getSession(true); // true: 세션이 없으면 새로 생성 (톰켓이 생성[Http 로 시작되는 모든것])
        // 세션에 인증 객체 저장
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return ResponseEntity.ok("Success"); // ok => 200 리턴
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // request: 클라이언트에서 오는 요청, response: 서버에서 오는 응답 결과
        HttpSession session = request.getSession(false); // 세션이 없으면 null, 세션이 있으면 받아옴
        if (session != null) { // 세션이 있으면
            session.invalidate(); // invalidate 함수는 세션을 없애고 세션에 속해있는 값들을 모두 없앤다.
        }
        return "You have been logged out.";
    }

    @GetMapping("current") // 현재 세션의 주인의 정보를 알고 싶을때 사용
    public SessionDto getCurrentUser() { // 리턴값 String 에서 SessionDto 로 변경
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        // 유저네임과 권한 Dto 에 적용
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserId(authentication.getName());
        sessionDto.setAuthority(authentication.getAuthorities());

        //return authentication.getName(); // 없는 경우 무명(anonymous), 있는 경우 유저네임
        return sessionDto; // 없는 경우 무명(anonymous), 있는 경우 유저네임 과 함께 권한도 같이 보냄
    }

    @GetMapping("/user/id/{userId}")
    public User getUserByUserId(@PathVariable String userId) {
        return userService.getUserByUserId(userId);
    }

}
