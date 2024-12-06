package org.hcv.chodoido_ute_service.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.LoginRequest;
import org.hcv.chodoido_ute_service.dto.request.LogoutRequest;
import org.hcv.chodoido_ute_service.dto.response.LoginResponse;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.exception.AuthenticationExceptionCustom;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.security.jwt.JwtInits;
import org.hcv.chodoido_ute_service.security.user.SUserDetails;
import org.hcv.chodoido_ute_service.service.Interface.ILoginService;
import org.hcv.chodoido_ute_service.service.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/auth")
public class AuthorController {
    JwtInits jwtInits;
    ILoginService loginService;
    IUserService userService;
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String tokenAccess = jwtInits.generateToken(authentication, false);
            String tokenRefresh = jwtInits.generateToken(authentication, true);
            SUserDetails userDetails = (SUserDetails) authentication.getPrincipal();
            UserDTO user = userService.findUser(userDetails.getEmail());

            LoginResponse loginResponse = LoginResponse.builder()
                    .id(user.getId()).email(user.getEmail()).token(tokenAccess).tokenRefresh(tokenRefresh).build();
            ResponseDTO<LoginResponse> responseDTO = new ResponseDTO<>();
            responseDTO.setStatus("success");
            responseDTO.setData(loginResponse);
            responseDTO.setMessage("Login success");
            return ResponseEntity.ok(responseDTO);
        }catch (Exception ex){
            throw new NoActionException("Email or password wrong");
        }
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String header){
        try{
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            String tokenAccess = jwtInits.generateToken(authentication, false);
            String tokenRefresh = jwtInits.generateToken(authentication, true);
            String token = header.substring(7);
            jwtInits.deleteToken(token);
            return ResponseEntity.ok(ResponseDTO.builder().status("success").data(LoginResponse.builder().token(tokenAccess).tokenRefresh(tokenRefresh).build()).build());
        }catch (Exception e){
            throw new AuthenticationExceptionCustom("Refresh token wrong");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOut(@RequestBody LogoutRequest logoutRequest){
        jwtInits.deleteToken(logoutRequest.getTokenAccess());
        jwtInits.deleteToken(logoutRequest.getTokenRefresh());
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("success").build());
    }

}
