package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.request.ServicePackageRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.entity.ServicePackage;
import org.hcv.chodoido_ute_service.service.Interface.IMissionService;
import org.hcv.chodoido_ute_service.service.Interface.IServicePackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicePackage")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {
    IServicePackageService servicePackageService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(servicePackageService.getListService()).build());
    }


    @GetMapping("/findServiceById/{id}")
    public ResponseEntity<?> findServiceById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(servicePackageService.findServiceById(id)).build());
    }
    @GetMapping("/findServiceDetailsById/{id}")
    public ResponseEntity<?> findServiceDetailsById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(servicePackageService.findServiceDetailsById(id)).build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody ServicePackageRequest servicePackageRequest){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(servicePackageService.update(servicePackageRequest)).build());
    }

    @PostMapping("/addService")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addService(@RequestBody ServicePackageRequest servicePackage){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(servicePackageService.addService(servicePackage)).build());
    }


    @PostMapping("/submitServiceToUser")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> submitServiceToUser(@RequestParam Long idUser, @RequestParam Long idService){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(servicePackageService.addServiceDetails(idUser, idService, false)).build());
    }
//
//    @PostMapping("/addList")
//    public ResponseEntity<?> addList(@RequestBody List<MissionRequest> missionRequest){
//        return ResponseEntity.ok(ResponseDTO.builder().status("success").data().build()missionService.addList(missionRequest));
//    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteService(@PathVariable Long id){
        servicePackageService.deleteService(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("Successfully deleted").build());
    }
    @DeleteMapping("/deleteServiceDetails/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteServiceDetails(@PathVariable Long id){
        servicePackageService.deleteServiceDetails(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("Successfully deleted").build());
    }
}
