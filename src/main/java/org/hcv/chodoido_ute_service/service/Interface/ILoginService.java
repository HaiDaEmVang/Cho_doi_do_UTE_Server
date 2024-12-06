package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.LoginRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;

public interface ILoginService {
    UserDTO login(LoginRequest loginRequest);
}
