package org.hcv.chodoido_ute_service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
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

    @Override
    public User userRequestToUser(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userRequest.getId() );
        user.email( userRequest.getEmail() );
        user.name( userRequest.getName() );
        user.nickName( userRequest.getNickName() );
        user.gender( userRequest.getGender() );
        user.password( userRequest.getPassword() );
        user.facebook( userRequest.getFacebook() );
        user.zalo( userRequest.getZalo() );
        user.local( userRequest.getLocal() );

        return user.build();
    }

    @Override
    public List<User> toUserList(List<UserRequest> userRequestList) {
        if ( userRequestList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userRequestList.size() );
        for ( UserRequest userRequest : userRequestList ) {
            list.add( userRequestToUser( userRequest ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> toUserResponseList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userList.size() );
        for ( User user : userList ) {
            list.add( userToUserDTO( user ) );
        }

        return list;
    }

    @Override
    public void updateUser(UserRequest userRequest, User user) {
        if ( userRequest == null ) {
            return;
        }

        user.setId( userRequest.getId() );
        user.setEmail( userRequest.getEmail() );
        user.setName( userRequest.getName() );
        user.setNickName( userRequest.getNickName() );
        user.setGender( userRequest.getGender() );
        user.setPassword( userRequest.getPassword() );
        user.setFacebook( userRequest.getFacebook() );
        user.setZalo( userRequest.getZalo() );
        user.setLocal( userRequest.getLocal() );
    }
}
