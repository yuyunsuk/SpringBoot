package dw.gameshop.controller;

import dw.gameshop.dto.BaseResponse;
import dw.gameshop.dto.LoginDto;
import dw.gameshop.dto.TokenDto;
import dw.gameshop.enumstatus.ResultCode;
import dw.gameshop.jwt.JwtFilter;
import dw.gameshop.jwt.TokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider,
                          AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<BaseResponse<TokenDto>> authenticate(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(),
                        loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject() // 기존 authenticationManager, 토큰 authenticationManagerBuilder
                .authenticate(authenticationToken); // 인증, 인증 실패시 예외가 발생하기 때문에 아래줄 실행 안됨

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication); // 토큰 생성 부터 Session 과 다름

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>( // T body, 상태코드 2개는 기존에 사용하던 생성자, 이번에는 3개 T body, 응답해더, 상태코드
                new BaseResponse<>(ResultCode.SUCCESS.name(), new TokenDto(jwt), ResultCode.SUCCESS.getMsg()),
                httpHeaders,
                HttpStatus.OK);
    }
}