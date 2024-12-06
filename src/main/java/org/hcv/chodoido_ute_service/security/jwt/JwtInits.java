package org.hcv.chodoido_ute_service.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.exception.AuthenticationExceptionCustom;
import org.hcv.chodoido_ute_service.security.user.SUserDetails;
import org.hcv.chodoido_ute_service.service.RedisService.IBaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JwtInits {
    @Value("${jwt.token.secret_Key}")
    private String JWT_SECRET;
    @Value("${jwt.token.expiration}")
    private Long JWT_EXPIRATION;
    @Value("${jwt.token.expiration_refresh}")
    private Long JWT_REFRESH_EXPIRATION;

    @Autowired
    private IBaseRedisService baseRedisService;

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }

    public String generateToken(Authentication authentication, boolean isRefresh){
        long Expiration = (new Date()).getTime() + ((isRefresh)? JWT_REFRESH_EXPIRATION: JWT_EXPIRATION);
        SUserDetails userDetails = (SUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .setExpiration(new Date(Expiration))
                .setIssuedAt(new Date())
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getObjectEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpirationTime(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public void deleteToken(String token){
        try{
            if(validateToken(token)){
                String email = this.getObjectEmail(token);
                Date tokenExpiration = this.getExpirationTime(token);
                long redisExpiration = tokenExpiration.getTime()  - (new Date()).getTime();
                if(redisExpiration > 0)
                    baseRedisService.save(token, email, redisExpiration, TimeUnit.MILLISECONDS);
            }
        }catch (Exception e){
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("Token expired: {}", ex.getMessage());
            throw new AuthenticationExceptionCustom("TOKEN HET HAN");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported token: {}", ex.getMessage());
            throw new AuthenticationExceptionCustom("TOKEN KHONG PHU HOP");
        } catch (MalformedJwtException ex) {
            log.error("Malformed token: {}", ex.getMessage());
            throw new AuthenticationExceptionCustom(ex.getMessage());
        } catch (SignatureException ex) {
            log.error("Invalid signature: {}", ex.getMessage());
            throw new AuthenticationExceptionCustom("TOKEN KHONG THE KY");
        } catch (IllegalArgumentException ex) {
            log.error("Illegal argument in token: {}", ex.getMessage());
            throw new AuthenticationExceptionCustom("TOKEN KO HOP LE");
        } catch (Exception ex) {
            log.error("Token validation error: {}", ex.getMessage());
            throw new AuthenticationExceptionCustom("TOKEN KHONG THE KY");
        }
    }
}
