package org.hcv.chodoido_ute_service.mapper;


import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "Spring")
public interface UserMapper  {
    UserDTO userToUserDTO(User user);

    @Mapping(target = "imgUrl", ignore = true)
    User userRequestToUser(UserRequest userRequest);
    List<User> toUserList(List<UserRequest> userRequestList);
    List<UserDTO> toUserResponseList(List<User> userList);
//    @Mapping(target = "imgUrl", ignore = true)
    void updateUser(UserRequest userRequest, @MappingTarget User user);

}
