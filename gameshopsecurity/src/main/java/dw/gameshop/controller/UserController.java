package dw.gameshop.controller;

import dw.gameshop.dto.UserDto;
import dw.gameshop.service.UserDetailService;
import dw.gameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), // 저장 Service 로 보냄
                HttpStatus.CREATED);
    }

    @PostMapping("login") // "/user/login" => @PostMapping("login") 에서 "/"를 붙이면 안됨
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate( // 보안 관련 코드이므로 바로 리턴
                new UsernamePasswordAuthenticationToken(userDto.getUserId(), userDto.getPassword()) // 토큰을 만듦
        );
        SecurityContextHolder.getContext().setAuthentication(authentication); // 보안컨텍스트홀더 인증 저장

        return ResponseEntity.ok("Success");
    }

    @GetMapping("current") // 현재 세션의 주인의 정보를 알고 싶을때 사용
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        return authentication.getName(); // 없는 경우 무명(anonymous), 있는 경우 유저네임
    }
}
