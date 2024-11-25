package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.entity.Mission;
import org.hcv.chodoido_ute_service.service.Interface.ICategoryService;
import org.hcv.chodoido_ute_service.service.Interface.IMissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MissionController {
    IMissionService missionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(missionService.findAll()).build());
    }

    @GetMapping("/getDateSign")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> getDateSign(@RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(missionService.getDateSign(idUser)).build());
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody MissionRequest missionRequest){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(missionService.add(missionRequest)).build());
    }

    @PostMapping("/addList")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addList(@RequestBody List<MissionRequest> missionRequest){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(missionService.addList(missionRequest)).build());
    }

    @PutMapping("/successMission")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> successMission(@RequestParam Long idUser, @RequestParam Long idMission){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(missionService.successMisstion(idUser, idMission)).build());
    }



    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        missionService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("Successfully deleted").build());
    }


}
