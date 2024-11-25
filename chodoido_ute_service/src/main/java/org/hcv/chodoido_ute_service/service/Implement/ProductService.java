package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.applicationInitConfig.ApplicationInitConfig;
import org.hcv.chodoido_ute_service.dto.request.ProductRequest;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.entity.*;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.ProductMapper;
import org.hcv.chodoido_ute_service.repository.*;
import org.hcv.chodoido_ute_service.service.AwsS3Service;
import org.hcv.chodoido_ute_service.service.Interface.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    PostProductStatus DEFAULT_POST_PRD_STATUS = PostProductStatus.CHO_DUYET;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;
    AwsS3Service awsS3Service;
    ProductMapper productMapper;
    UserService userService;
    String URL_IMG = "images/post";
    String URL_VIDEO = "videos";

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findProductView(PostProductStatus.DA_DUYET).stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public List<ProductDTO> getProductForAdmin() {
        return productRepository.findProductView(PostProductStatus.CHO_DUYET).stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public ProductDTO findDTOById(Long id) {
        return productMapper.toProductDTO(productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Not found Product by id: "+ id)));
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Not found Product by id: "+ id));
    }

    @Override
    public ProductDTO update(ProductRequest productRequest, MultipartFile[] fileImage, MultipartFile[] fileVideo) {
        Product product = productRepository.findById(productRequest.getId())
                .orElseThrow(() -> new NotFoundException("Not found Product by id: "+ productRequest.getId()));

        productMapper.updateProduct(product, productRequest);

        for (MultipartFile multipartFile : fileImage) {
            ProductImg productImg = new ProductImg();
            productImg.setUrlImg(awsS3Service.saveImageToS3(multipartFile, URL_IMG));
            productImg.setProduct(product);
            product.getProductImgs().add(productImg);
        }

        for(int i = 0; i< fileImage.length; i++){
            ProductVideo productVideo =
                    ProductVideo.builder().product(product)
                            .videoUrl(awsS3Service.saveImageToS3(fileVideo[i], URL_VIDEO)).build();
            product.getProductVideos().add(productVideo);
        }
        return productMapper.toProductDTO(productRepository.save(product));

    }

    @Override
    public ProductDTO updatePostStatusProduct(Long idProduct, PostProductStatus status) {
        Product product = this.findById(idProduct);
        product.setPostProductStatus(status);
        if(status == PostProductStatus.DA_DUYET)
            if(ApplicationInitConfig.MISSION_DANGBAITHANHCONG != null)
                product.getUser().setPoint(ApplicationInitConfig.MISSION_MUAHANGTHANHCONG.getPoint());
            else
                log.error("User can not set point/ mission default not init /");
        if(status == PostProductStatus.DA_TUCHOI)
            product.getUser().setCountPost(1L);
        product.getUser().setProductLost();
        product.getUser().setProductSuccess();
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO addProduct(ProductRequest productRequest, MultipartFile[] fileImage, MultipartFile[] fileVideo) {
        productRequest.setId(0L);
        Product product = new Product();
        productMapper.updateProduct(product, productRequest);

        User user = userService.findByEmail(productRequest.getEmail());
        if(user.getCountPost() < 1)
            throw new NoActionException("User not enough countPost. please get/update service");
        user.setCountPost(-1L);
        userRepository.save(user);

        product.setUser(user);
        Category category = categoryRepository.findById(productRequest.getIdCategory())
                        .orElseThrow(()-> new NotFoundException("Not found category with id: "+ productRequest.getIdCategory()));
        product.setCategory(category);
        product.setTimePost(LocalDateTime.now());
        product.setPostProductStatus(DEFAULT_POST_PRD_STATUS);

        for (MultipartFile multipartFile : fileImage) {
            ProductImg productImg = new ProductImg();
            productImg.setUrlImg(awsS3Service.saveImageToS3(multipartFile, URL_IMG));
            productImg.setProduct(product);
            product.getProductImgs().add(productImg);
        }

        for (MultipartFile multipartFile : fileVideo) {
            ProductVideo productVideo =
                    ProductVideo.builder().product(product)
                            .videoUrl(awsS3Service.saveImageToS3(multipartFile, URL_VIDEO)).build();
            product.getProductVideos().add(productVideo);
        }

        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findByCategory(Long idCategory) {
        if(idCategory == -1)
            return this.findAll();
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(()-> new NotFoundException("Not found Category by id: "+ idCategory));
        return productRepository.findByCategory(category, PostProductStatus.DA_DUYET).stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public List<ProductDTO> findByUser(Long idUser) {
        User user  = userRepository.findById(idUser)
                .orElseThrow(()-> new NotFoundException("Not found User by id: " + idUser));
        return productRepository.findByUser(user).stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public List<ProductDTO> search(String keyword) {
        return productRepository.findByTitle(keyword, PostProductStatus.DA_DUYET).stream().map(productMapper::toProductDTO).toList();
    }

    @Override
    public List<ProductDTO> findProductAndPostStatus(PostProductStatus postStatus, Long idUser) {
        User user  = userRepository.findById(idUser)
                .orElseThrow(()-> new NotFoundException("Not found User by id: " + idUser));
        return productRepository.findByUserAndPostProductStatus( postStatus, user)
                .stream().map(productMapper::toProductDTO).toList();
    }
}
