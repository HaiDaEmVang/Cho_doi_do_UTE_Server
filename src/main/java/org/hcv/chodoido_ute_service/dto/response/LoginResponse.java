package org.hcv.chodoido_ute_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Long id;
    private String email;
    private String token;
    private String tokenRefresh;
}
