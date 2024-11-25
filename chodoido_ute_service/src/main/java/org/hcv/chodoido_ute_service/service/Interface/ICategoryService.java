package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category update(Long id, String name, MultipartFile categoryImage);
    Category add(String name, MultipartFile categoryImage);

    void delete(Long id);
}
