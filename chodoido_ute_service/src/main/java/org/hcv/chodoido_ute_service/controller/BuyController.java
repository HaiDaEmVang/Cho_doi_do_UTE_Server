package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.BuyRequest;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.service.Interface.IBuyService;
import org.hcv.chodoido_ute_service.service.Interface.IUserFollower;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/buy")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BuyController {
    IBuyService buyService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#buyRequest.idUser, principal.username)")
    public ResponseEntity<?> add(@RequestBody BuyRequest buyRequest){
        if(buyService.addBuy(buyRequest) != null)
            return ResponseEntity.ok(ResponseDTO.builder().status("success").build());
        else throw new NoActionException("Thêm thành công");
    }

    @PutMapping("/update_status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestParam Long idBuy, @RequestParam BuyStatus status){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.updateStatus(idBuy, status)).build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        buyService.deleteBuy(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("delete success").build());
    }

    @GetMapping("/find_by_status_and_user")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> findByStatusAndUser(@RequestParam BuyStatus status, @RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.findByStatusAndUser(status, idUser)).build());
    }

    @GetMapping("/count_product_by_user")
    public ResponseEntity<?> countProductByUser( @RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.countProductByUser(idUser)).build());
    }
}
