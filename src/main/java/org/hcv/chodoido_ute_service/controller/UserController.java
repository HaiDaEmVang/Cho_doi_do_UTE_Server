
package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.security.CustomSecurity;
import org.hcv.chodoido_ute_service.service.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;
    private final CustomSecurity customSecurity;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userService.findAllUsers()).build());
    }

    @GetMapping("/public/{email}")
    public ResponseEntity<?> getUserPublic(@PathVariable String email){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userService.findUser(email)).build());
    }

    @GetMapping("/me/{email}")
    @PreAuthorize("hasRole('ADMIN') or #email == principal.username")
    public ResponseEntity<?> getUser(@PathVariable String email){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userService.findUser(email)).build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or #user.email == principal.username")
    public ResponseEntity<?> updateUser(@RequestPart("imgUrl") MultipartFile imgUrl, @RequestPart("user") UserRequest user){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userService.update(imgUrl, user)).build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> regiterUser(@RequestBody UserRequest user){
        if(user.getEmail().isEmpty() || user.getName().isEmpty())
            throw new NoActionException("Dữ liệu không đầy đủ");
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userService.addUser(user)).build());
    }

    @PostMapping("/registerList")
    public ResponseEntity<?> registerUserList(@RequestBody List<UserRequest> userList) {
        userService.addList(userList);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("Them ds thanh cong").build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#id, principal.username)")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("Xóa thành công").build());
    }
}
