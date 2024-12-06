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
        else throw new NoActionException("Thêm không thành công");
    }

    @PutMapping("/update_status")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isSuccessUpdateBuy(#idBuy, principal.username, #status)")
    public ResponseEntity<?> update(@RequestParam Long idBuy, @RequestParam String status){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.updateStatus(idBuy, BuyStatus.valueOf(status))).build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwnerBuy(#idBuy, principal.username)")
    public ResponseEntity<?> update(@RequestParam Long idBuy, @RequestParam Long count){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.update(idBuy, count)).build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwnerBuy(#id, principal.username)")
    public ResponseEntity<?> delete(@PathVariable Long id){
        buyService.deleteBuy(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("delete success").build());
    }

    @GetMapping("/find_by_status_and_user")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> findByStatusAndUser(@RequestParam BuyStatus status, @RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.findByStatusAndUser(status, idUser)).build());
    }


    @GetMapping("/find_by_user")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> findByUser(@RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.findByUser(idUser)).build());
    }

    @GetMapping("/find_by_user_bought")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> find_by_user_bought(@RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.findByUserBought(idUser)).build());
    }

    @GetMapping("/count_product_by_user")
    public ResponseEntity<?> countProductByUser( @RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(buyService.countProductByUser(idUser)).build());
    }
}
