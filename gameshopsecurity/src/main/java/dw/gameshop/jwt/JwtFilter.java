package dw.gameshop.jwt;

import dw.gameshop.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

// 상속, doFilter, resolveToken
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // addFilterBefore, (ServletRequest[요청 서블릿], ServletResponse[응답 서블릿], FilterChain[다음번 필터 체인])
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest); // 토큰을 가져옴
        String requestURI = httpServletRequest.getRequestURI(); // 토큰의 주소

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { // 토큰이 있고 검증이 되었으면
            Authentication authentication = tokenProvider.getAuthentication(jwt); // 로그인시 authentication 가 만들어짐, 인증정보를 빼냄
            SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder 에 인증정보를 넣음
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse); // 체인으로 연결되어 있음, 다음 필터 체인을 실행시키라는 부분
    }

    // 토큰 담는 규칙에 따라 가져옴
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // 규칙 "Bearer "(7자리) 이후 토큰 작성
            return bearerToken.substring(7);
        }

        return null;
    }
}