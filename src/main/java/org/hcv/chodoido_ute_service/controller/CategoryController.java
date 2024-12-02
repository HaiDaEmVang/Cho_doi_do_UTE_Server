package org.hcv.chodoido_ute_service.controller;

import com.amazonaws.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.response.LoginResponse;
import org.hcv.chodoido_ute_service.dto.response.ResponseDTO;
import org.hcv.chodoido_ute_service.entity.Category;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.service.Interface.ICategoryService;
import org.hcv.chodoido_ute_service.service.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<Category>>> getAllCategory(){
        ResponseDTO<List<Category>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(categoryService.findAll());
        responseDTO.setStatus("success");
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(categoryService.findById(id)).build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(
            @RequestParam Long id, @RequestParam String name, @RequestParam MultipartFile categoryFile){
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(categoryService.update(id, name, categoryFile)).build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCategory(
            @RequestParam String name, @RequestParam("categoryImg") MultipartFile categoryFile){
        if(name == null || categoryFile.isEmpty())
            throw new NoActionException("Dữ liệu không đầy đủ");
        return ResponseEntity.ok(ResponseDTO.builder().status("success").data(categoryService.add(name, categoryFile)).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder().status("success").build());
    }
}
