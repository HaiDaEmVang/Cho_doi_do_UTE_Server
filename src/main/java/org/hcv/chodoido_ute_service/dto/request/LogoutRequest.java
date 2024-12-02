package org.hcv.chodoido_ute_service.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LogoutRequest {
    String tokenAccess;
    String tokenRefresh;
}
