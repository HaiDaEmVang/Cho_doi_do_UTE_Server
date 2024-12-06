package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.BuyRequest;
import org.hcv.chodoido_ute_service.dto.request.CommentRequest;
import org.hcv.chodoido_ute_service.dto.request.ProductRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.security.CustomSecurity;
import org.hcv.chodoido_ute_service.service.Interface.IBuyService;
import org.hcv.chodoido_ute_service.service.Interface.ICommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    ICommentService commentService;
    private final CustomSecurity customSecurity;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#commentRequest.idUser, principal.username)")
    public ResponseEntity<?> add(@RequestPart("comment") CommentRequest commentRequest,
                                 @RequestPart("images") MultipartFile[] image){
        if(commentRequest == null || image == null){
            throw new NoActionException("Dữ liệu không chính xác");
        }
        return  ResponseEntity.ok(ResponseDTO.builder().status("success").data(commentService.add(commentRequest, image)).build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#commentRequest.idUser, principal.username)")
    public ResponseEntity<?> update(@RequestPart("comment") CommentRequest commentRequest,
                                 @RequestPart("images") MultipartFile[] image){
        if(commentRequest == null || image == null){
            throw new NoActionException("Dữ liệu không chính xác");
        }
        return  ResponseEntity.ok(ResponseDTO.builder().status("success").data(commentService.update(commentRequest, image)).build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwnerComment(#id, principal.username)")
    public ResponseEntity<?> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").build());
    }

    @GetMapping("/find_by_product")
    public ResponseEntity<?> getCommentDTOByProduct(@RequestParam Long idProduct){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(commentService.getCommentDTOByProduct(idProduct)).build());
    }

    @GetMapping("/find_by_user")
    public ResponseEntity<?> getCommentDTOByUser( @RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(commentService.getCommentDTOByUser(idUser)).build());
    }

    @GetMapping("/find_commented_by_user")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> getCommentedDTOByUser( @RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(commentService.getCommentedDTOByUser(idUser)).build());
    }
}
