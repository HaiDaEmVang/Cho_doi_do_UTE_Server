package org.hcv.chodoido_ute_service.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        HashMap<String, Object> bodyError = new HashMap<>();
        bodyError.put("Error", "UNAUTHORIZED");

        String exceptionMessage = (String) request.getAttribute("exceptionMessage");
        if (exceptionMessage != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            bodyError.put("message", exceptionMessage);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            bodyError.put("message", authException.getMessage());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), bodyError);
    }
}
