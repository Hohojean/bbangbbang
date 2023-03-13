package teamPro.bbangShuttle.security;

import com.fasterxml.classmate.AnnotationOverrides;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.vo.MemberVO;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
  private static final String SECRET_KEY = "NMA8JPctFuna59f5";

  // 1. JWT Token 생성
  public String create(MemberVO user) {
    // 기한 현재시간 으로부터 1일로 설정
    Date expiryDate = Date.from(
        Instant.now()
            .plus(1, ChronoUnit.DAYS));
    // => 기한설정: 현재시간 으로부터 차이가 +1일 되는 날 설정

    return Jwts.builder()
        // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        // payload에 들어갈 내용
        .setSubject(user.getUserID()) // sub : subject
        .setIssuer("demo app") // iss : Issuer, 발급 주체
        .setIssuedAt(new Date()) // iat : Issued At, 토큰이 발급된 시간
        .setExpiration(expiryDate) // exp : Expiration, 만료시간
        .compact();
  }

  // 2. 검증
  // => 토큰을 디코딩 및 파싱 하여 토크의 위조여부 확인
  // => 이후 user의 id를 return
  public String validateAndGetUserId(String token) {
    // parseClaimsJws메서드가 Base 64로 디코딩 및 파싱.
    // 즉, 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명 후, token의 서명 과 비교.
    // 위조되지 않았다면 페이로드(Claims) 리턴
    // 그 중 우리는 user의 id가 필요하므로 getBody를 부른다.
    Claims claims = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

  // JWT Token 폐기
  public boolean invalidateToken(String token) {
    try {
      // parseClaimsJws 메서드가 예외를 던지지 않으면 해당 토큰이 유효하다는 뜻
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true; // 토큰이 유효한 경우 true를 반환
    } catch (JwtException e) {
      return false; // 토큰이 유효하지 않은 경우 false를 반환
    }
  }
}
