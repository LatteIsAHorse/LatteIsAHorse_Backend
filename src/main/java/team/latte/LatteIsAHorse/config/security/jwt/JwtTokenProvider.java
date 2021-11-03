package team.latte.LatteIsAHorse.config.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import team.latte.LatteIsAHorse.model.user.RoleType;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private long tokenValidMillisecond = 5 * 24 * 60 * 60 * 1000L;

    private final UserDetailsService userDetailsService;
    private final RedisTemplate<String, String> logoutRedis;


    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * JWT Token을 생성합니다.
     */
    public String generateToken(Long userId, String email, RoleType role){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()  + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * JWT Token에서 인증정보를 조회합니다.
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Request Header에서 JWT TOKEN 값을 가져옵니다.
     */
    public String resolveToken(HttpServletRequest req){
        return req.getHeader("AUTH");
    }

    /**
     * JWT Token에서 Email을 추출합니다.
     */
    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * 만료기간 확인
     */
    public Date getExpirationDate(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claims.getBody().getExpiration();
    }

    /**
     * JWT Token의 유효성을 체크한다.
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = getClaims(token);

            if(logoutRedis.opsForValue().get(token) != null) {
                log.info(("이미 로그아웃 처리된 사용자입니다."));
                return false;
            }
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private Jws<Claims> getClaims(String jwt) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
    }
}

