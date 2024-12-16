package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAllUsers();
    List<User> findAll();
    UserDTO findUser(String email);
    UserDTO findUser(Long id);
    UserDTO update(MultipartFile multipartFile, UserRequest userRequest);
    UserDTO addUser( UserRequest userRequest);
    User addUser(User user);
    void addList(List<UserRequest> list);

    void delete(Long id);

}
