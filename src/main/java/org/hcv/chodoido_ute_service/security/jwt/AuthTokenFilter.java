package org.hcv.chodoido_ute_service.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.security.user.SUserDetails;
import org.hcv.chodoido_ute_service.security.user.SUserDetailsService;
import org.hcv.chodoido_ute_service.service.RedisService.IBaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtInits jwtInits;
    @Autowired
    private SUserDetailsService userDetailsService;
    @Autowired
    private IBaseRedisService baseRedisService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = parseToToken(request);
        if(token != null){
            try{
                if (jwtInits.validateToken(token) && !baseRedisService.isExists(token)) {
                    String email = jwtInits.getObjectEmail(token);
                    SUserDetails userDetails = (SUserDetails) userDetailsService.loadUserByUsername(email);
                    var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    request.setAttribute("exceptionMessage", "token in black list token, please send token refresh or login in account");
                }
            }catch (AuthenticationException ex){
                request.setAttribute("exceptionMessage", ex.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    public String parseToToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.contains("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
}
