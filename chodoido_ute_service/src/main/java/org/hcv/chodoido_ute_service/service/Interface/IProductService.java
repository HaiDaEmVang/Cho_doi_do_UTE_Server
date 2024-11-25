package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.ProductRequest;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.entity.Category;
import org.hcv.chodoido_ute_service.entity.PostProductStatus;
import org.hcv.chodoido_ute_service.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> getProductForAdmin();
    ProductDTO findDTOById(Long id);
    Product findById(Long id);
    ProductDTO update(ProductRequest productRequest, MultipartFile[] fileImage, MultipartFile[] fileVideo);
    ProductDTO updatePostStatusProduct(Long idProduct, PostProductStatus status);
    ProductDTO addProduct(ProductRequest productRequest, MultipartFile[] fileImage, MultipartFile[] fileVideo);
    Product save(Product product);
    void delete(Long id);

    List<ProductDTO> findByCategory(Long idCategory);
    List<ProductDTO> findByUser(Long idUser);
    List<ProductDTO> search(String keyword);
    List<ProductDTO> findProductAndPostStatus(PostProductStatus postStatus, Long idUser);


}
