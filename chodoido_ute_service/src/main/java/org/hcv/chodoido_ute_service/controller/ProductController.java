package org.hcv.chodoido_ute_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.ProductRequest;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.entity.PostProductStatus;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.service.Interface.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll (){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findAll()).build());
    }

    @GetMapping("/getProductForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductForAdmin (){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.getProductForAdmin()).build());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if(id == null)
            throw new NoActionException("Id invalid");
        log.error("looi" +id.toString());
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findDTOById(id)).build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or #productRequest.email == principal.username")
    public ResponseEntity<?> update(@RequestPart("product") ProductRequest productRequest,
                                    @RequestPart("images") MultipartFile[] imageFile,
                                    @RequestPart("videos") MultipartFile[] videoFile){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.update(productRequest, imageFile, videoFile)).build());
    }

    @PutMapping("/update/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(@RequestParam Long idProduct,
                                          @RequestParam PostProductStatus status){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.updatePostStatusProduct(idProduct, status)).build());
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or #productRequest.email == principal.username")
    public ResponseEntity<?> add(@RequestPart("product") ProductRequest productRequest,
                                 @RequestPart("images") MultipartFile[] imageFile,
                                 @RequestPart("videos") MultipartFile[] videoFile){
        if(productRequest == null || (imageFile == null && videoFile == null))
            throw new NoActionException("Dữ liệu không đúng");
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.addProduct(productRequest, imageFile, videoFile)).build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwnerProduct(#id, principal.username)")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data("success").build());
    }


    @GetMapping("/findByCategory")
    public ResponseEntity<?> findByCategory(@RequestParam Long idCategory){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findByCategory(idCategory)).build());
    }

    @GetMapping("/findAllByUser")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> findByUser(@RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findByUser(idUser)).build());
    }

    @GetMapping("/findPublicByUser")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> findPublicByUser(@RequestParam Long idUser){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findProductByUserAndPostStatus(PostProductStatus.DA_DUYET, idUser)).build());
    }

    @GetMapping("")
    public ResponseEntity<?> search(@RequestParam String value){
        if(value == null || value.isEmpty())
            return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findAll()).build());
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.search(value)).build());
    }

    @GetMapping("/findBySatatus")
    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOwner(#idUser, principal.username)")
    public ResponseEntity<?> findMyProduct(@RequestParam Long idUser,
                                                       @RequestParam PostProductStatus postProductStatus){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(productService.findProductByUserAndPostStatus(postProductStatus, idUser)).build());
    }

}
