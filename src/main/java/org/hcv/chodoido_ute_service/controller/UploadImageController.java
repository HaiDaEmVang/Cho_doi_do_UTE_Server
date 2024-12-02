package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.BuyRequest;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.service.Interface.IBuyService;
import org.hcv.chodoido_ute_service.service.Interface.IUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadImageController {
    IUploadService uploadService;

    @PostMapping("/images")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addImages(@RequestParam MultipartFile[] files){
        return ResponseEntity.ok(uploadService.uploadImage(files));
    }
    @PostMapping("/videos")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addVideos(@RequestParam MultipartFile[] files){
        return ResponseEntity.ok(uploadService.uploadVideo(files));
    }

}
