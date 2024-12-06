package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.LoginRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.User;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.UserMapper;
import org.hcv.chodoido_ute_service.repository.UserRepository;
import org.hcv.chodoido_ute_service.service.Interface.ILoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginService implements ILoginService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO login(LoginRequest loginRequest) {
        User u = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new NotFoundException("Not found user with email " + loginRequest.getEmail()));

        if(!passwordEncoder.matches(loginRequest.getPassword(), u.getPassword())){
            throw  new NotFoundException("Email or password wrong!!");
        }
        return userMapper.userToUserDTO(u);
    }
}
