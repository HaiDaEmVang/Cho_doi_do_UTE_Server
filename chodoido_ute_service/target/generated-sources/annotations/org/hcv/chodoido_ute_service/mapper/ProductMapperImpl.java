package org.hcv.chodoido_ute_service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.hcv.chodoido_ute_service.dto.request.ProductRequest;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.ProductImg;
import org.hcv.chodoido_ute_service.entity.ProductVideo;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.id( product.getId() );
        productDTO.title( product.getTitle() );
        productDTO.description( product.getDescription() );
        productDTO.price( product.getPrice() );
        productDTO.timePost( product.getTimePost() );
        if ( product.getCount() != null ) {
            productDTO.count( product.getCount().intValue() );
        }
        productDTO.user( userToUserDTO( product.getUser() ) );
        productDTO.category( product.getCategory() );
        productDTO.postProductStatus( product.getPostProductStatus() );
        List<ProductImg> list = product.getProductImgs();
        if ( list != null ) {
            productDTO.productImgs( new ArrayList<ProductImg>( list ) );
        }
        List<ProductVideo> list1 = product.getProductVideos();
        if ( list1 != null ) {
            productDTO.productVideos( new ArrayList<ProductVideo>( list1 ) );
        }

        return productDTO.build();
    }

    @Override
    public void updateProduct(Product product, ProductRequest productRequest) {
        if ( productRequest == null ) {
            return;
        }

        product.setCount( productRequest.getCount() );
        product.setId( productRequest.getId() );
        product.setTitle( productRequest.getTitle() );
        product.setDescription( productRequest.getDescription() );
        product.setPrice( productRequest.getPrice() );
        product.setNew( productRequest.isNew() );
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.email( user.getEmail() );
        userDTO.name( user.getName() );
        userDTO.nickName( user.getNickName() );
        userDTO.gender( user.getGender() );
        userDTO.imgUrl( user.getImgUrl() );
        userDTO.facebook( user.getFacebook() );
        userDTO.zalo( user.getZalo() );
        userDTO.local( user.getLocal() );
        userDTO.productLost( user.getProductLost() );
        userDTO.productSuccess( user.getProductSuccess() );
        userDTO.point( user.getPoint() );
        userDTO.countPost( user.getCountPost() );
        userDTO.role( user.getRole() );

        return userDTO.build();
    }
}
