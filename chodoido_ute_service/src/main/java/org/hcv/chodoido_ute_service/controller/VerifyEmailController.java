
package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.security.CustomSecurity;
import org.hcv.chodoido_ute_service.service.Interface.IUserService;
import org.hcv.chodoido_ute_service.service.RedisService.BaseRedisService;
import org.hcv.chodoido_ute_service.service.emailService.VerifyEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerifyEmailController {
    VerifyEmailService verifyEmailService;

    @PostMapping("/sendCode/{email}")
    public ResponseEntity<?> getAllUsers(@PathVariable String email){
        verifyEmailService.sendCodeVerifyEmail(email);
        return ResponseEntity.ok().body(ResponseDTO.builder().status("success").data("SendSuccess").build());
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam String code){
        if(verifyEmailService.verifyCode(email, code))
            return ResponseEntity.ok().body(ResponseDTO.builder().status("success").data("SendSuccess").build());
        throw new NoActionException("Mã không khớp\nVui lòng thử lại");
    }

}
