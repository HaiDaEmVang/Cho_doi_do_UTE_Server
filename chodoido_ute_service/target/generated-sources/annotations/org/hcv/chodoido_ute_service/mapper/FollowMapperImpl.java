package org.hcv.chodoido_ute_service.mapper;

import javax.annotation.processing.Generated;
import org.hcv.chodoido_ute_service.dto.response.FollowDTO;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Follower;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public FollowDTO toFollowDTO(Follower follower) {
        if ( follower == null ) {
            return null;
        }

        FollowDTO.FollowDTOBuilder followDTO = FollowDTO.builder();

        followDTO.id( follower.getId() );
        followDTO.userFollow( userToUserDTO( follower.getUserFollow() ) );
        followDTO.userFollower( userToUserDTO( follower.getUserFollower() ) );

        return followDTO.build();
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
