package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.entity.Category;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.repository.CategoryRepository;
import org.hcv.chodoido_ute_service.service.AwsS3Service;
import org.hcv.chodoido_ute_service.service.Interface.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    CategoryRepository categoryRepository;
    AwsS3Service awsS3Service;
    String URL_IMG = "images/category";


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category by id: " + id.toString()));
    }

    @Override
    public Category update(Long id, String name, MultipartFile categoryImage) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category by id: " + id.toString()));
        category.setName(name);
        if(categoryImage != null)
            category.setUrl(awsS3Service.saveImageToS3(categoryImage,   URL_IMG));
        return categoryRepository.save(category);
    }

    @Override
    public Category add(String name, MultipartFile categoryImage) {
        Category category = new Category();
        category.setName(name);
        category.setUrl(awsS3Service.saveImageToS3(categoryImage, URL_IMG));
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
