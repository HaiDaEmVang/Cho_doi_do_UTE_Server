package org.hcv.chodoido_ute_service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.hcv.chodoido_ute_service.dto.response.BuyDTO;
import org.hcv.chodoido_ute_service.dto.response.ProductDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Buy;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
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
public class BuyMapperImpl implements BuyMapper {

    @Override
    public BuyDTO buyToBuyDTO(Buy buy) {
        if ( buy == null ) {
            return null;
        }

        BuyDTO.BuyDTOBuilder buyDTO = BuyDTO.builder();

        buyDTO.id( buy.getId() );
        buyDTO.user( userToUserDTO( buy.getUser() ) );
        buyDTO.product( productToProductDTO( buy.getProduct() ) );
        buyDTO.timeBuy( buy.getTimeBuy() );
        if ( buy.getStatus() != null ) {
            buyDTO.status( buy.getStatus().name() );
        }
        buyDTO.count( buy.getCount() );
        buyDTO.price( buy.getPrice() );
        buyDTO.isComment( buy.getIsComment() );

        return buyDTO.build();
    }

    @Override
    public Buy updateBuy(BuyDTO buyDTO) {
        if ( buyDTO == null ) {
            return null;
        }

        Buy.BuyBuilder buy = Buy.builder();

        buy.id( buyDTO.getId() );
        buy.product( productDTOToProduct( buyDTO.getProduct() ) );
        buy.user( userDTOToUser( buyDTO.getUser() ) );
        buy.timeBuy( buyDTO.getTimeBuy() );
        buy.count( buyDTO.getCount() );
        buy.price( buyDTO.getPrice() );
        if ( buyDTO.getStatus() != null ) {
            buy.status( Enum.valueOf( BuyStatus.class, buyDTO.getStatus() ) );
        }
        buy.isComment( buyDTO.getIsComment() );

        return buy.build();
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

    protected ProductDTO productToProductDTO(Product product) {
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

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.email( userDTO.getEmail() );
        user.name( userDTO.getName() );
        user.nickName( userDTO.getNickName() );
        user.gender( userDTO.getGender() );
        user.imgUrl( userDTO.getImgUrl() );
        user.facebook( userDTO.getFacebook() );
        user.zalo( userDTO.getZalo() );
        user.local( userDTO.getLocal() );
        user.productLost( userDTO.getProductLost() );
        user.productSuccess( userDTO.getProductSuccess() );
        user.point( userDTO.getPoint() );
        user.countPost( userDTO.getCountPost() );
        user.role( userDTO.getRole() );

        return user.build();
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.getId() );
        product.title( productDTO.getTitle() );
        product.description( productDTO.getDescription() );
        product.price( productDTO.getPrice() );
        product.timePost( productDTO.getTimePost() );
        product.count( (long) productDTO.getCount() );
        product.postProductStatus( productDTO.getPostProductStatus() );
        product.user( userDTOToUser( productDTO.getUser() ) );
        product.category( productDTO.getCategory() );
        List<ProductImg> list = productDTO.getProductImgs();
        if ( list != null ) {
            product.productImgs( new ArrayList<ProductImg>( list ) );
        }
        List<ProductVideo> list1 = productDTO.getProductVideos();
        if ( list1 != null ) {
            product.productVideos( new ArrayList<ProductVideo>( list1 ) );
        }

        return product.build();
    }
}
