package teamPro.bbangShuttle.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      // 1) request 에서 토큰 가져오기.
      String token = parseBearerToken(request);
      log.info("Filter is running...");

      if (token != null && !token.equalsIgnoreCase("null")) {

        // 2) 토큰 검증 & userId 가져오기
        //    JWT이므로 인가 서버에 요청 하지 않고도 검증 가능
        //    TokenProvider 의 검증메서드를 통해 검증후 id 전달받음 (위조된 경우 예외처리 됨)
        String userId = tokenProvider.validateAndGetUserId(token);
        log.info("Authenticated user ID : " + userId );

        // 3) 인증 완료
        // => UsernamePasswordAuthenticationToken 에 user의 인증정보를 저장.
        //    UsernamePasswordAuthenticationToken 생성자의 첫 매개변수가 AuthenticationPrincipal (인증정보 or 인증본인) 이며
        //    @AuthenticationPrincipal 으로 사용할 수 있다.
        // => SecurityContextHolder에 인증된 user를 등록한다.
        // => SecurityContextHolder에 등록해야 인증된 user라고 생각하고, user를 인식한다.
        AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userId, // 인증된 사용자의 정보. 문자열이 아니어도 아무거나 넣을 수 있다.
            null, //
            AuthorityUtils.NO_AUTHORITIES
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        // => SecurityContextHolder.createEmptyContext() 메서드로 생성하고
        //    생성된 SecurityContext 에 인증정보를 넣고
        //    다시 SecurityContextHolder 에 컨텍스트로 등록함.
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
      }
    } catch (Exception ex) {
      logger.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);
  }

  // ** Bearer Token
  // => HTTP통신에서 사용하는 인증 방식에 Bearer Authentication을 사용하는 것이다.
  // => Bearer Authentication이란, "이 토큰을 나르는(bearer) 사람에게 권한을 부여하시오"라는 것

  // ** StringUtils
  // => import org.springframework.util.StringUtils;
  // => 거의 대부분의 문자열 처리를 수행할 수 있음.
  // => 파라미터 값으로 null을 주더라도 NullPointException을 발생시키지 않고,
  //    메서드에 따라 알맞은 결과를 리턴한다.
  // => 주요 method 정리
  //	- hasText() : null, 길이 0, 공백("" or " ") 중 하나라도 있으면 false를 반환함.
  // 	- hasLength() : null 체크 후, 길이가 0인지 판별한다.
  //				 	( 공백만 있는 문자열, " " 도 true가 반환되는 점을 주의 )
  //					공백으로만 이루어졌더라도 상관없이 null 체크와 길이가 1 이상인지 확인할때 사용.
  // 	- isEmpty() : null, 길이 0, 공백("" or " ") 중 하나라도 있으면 false를 반환함. (위의 hasText() 권장함)
  // 	- deleteWhitespace() , trim() : 문자열에 공백 문자가 있으면 모두 제거
  // 	- equals() : 파라미터 값의 동일성

  private String parseBearerToken(HttpServletRequest request) {
    // => Http request 의 헤더를 파싱해 Bearer 토큰을 리턴한다.

    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
