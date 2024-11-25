package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.service.Interface.IUserFollower;
import org.hcv.chodoido_ute_service.service.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/follower")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowerController {
    IUserFollower userFollower;

    @PostMapping("/add_follower")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @customSecurity.isOwner(#idFollow, principal.username)")
    public ResponseEntity<?> addFollower(@RequestParam Long idFollow, @RequestParam Long idFollower){
        if(userFollower.addFollower(idFollow, idFollower) != null)
            return ResponseEntity.ok(ResponseDTO.builder().status("success").data("Them thanh cong").build());
        else throw new NoActionException("add err");
    }

    @DeleteMapping("/remove_follower")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @customSecurity.isOwner(#idFollow, principal.username)")
    public ResponseEntity<?> removeFollower(@RequestParam Long idFollow, @RequestParam Long idFollower){
        userFollower.removeFollower(idFollow, idFollower);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("delete success").build());
    }

    @GetMapping("/find_list_user_followers")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #userUpdateRequest.email == principal.username)")
    public ResponseEntity<?> findListUserFollowers(@RequestParam Long idFollow){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userFollower.findListUserFollowers(idFollow)).build());
    }


    @GetMapping("/count_followers")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @customSecurity.isOwner(#idFollow, principal.username)")
    public ResponseEntity<?> countFollowers(
            @RequestParam Long idFollow){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(userFollower.countFollowers(idFollow)).build());
    }
}
