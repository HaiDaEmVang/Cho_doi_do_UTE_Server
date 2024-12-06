package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hcv.chodoido_ute_service.dto.request.UserRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.Role;
import org.hcv.chodoido_ute_service.entity.User;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.UserMapper;
import org.hcv.chodoido_ute_service.repository.UserRepository;
import org.hcv.chodoido_ute_service.service.AwsS3Service;
import org.hcv.chodoido_ute_service.service.Interface.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    MissionService missionService;
    ServicePackageService servicePackageService;
    private String IMAGER_USER_DEFAULT = "https://chodoidoute.s3.ap-southeast-1.amazonaws.com/imageDefault/UserDefault.jpg";

    UserMapper userMapper;
    AwsS3Service awsS3Service;
    PasswordEncoder passwordEncoder;

    Role ROLE_DEFAULT = Role.USER;
    Long ID_SERVICE_DEFAULT = 1L;
    String PATH_IMAGE = "images/user";

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).toList();
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public UserDTO findUser(String email) {
        return userMapper.userToUserDTO(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new NotFoundException("User not found with email: " + email))
        );
    }

    @Override
    public UserDTO update(MultipartFile multipartFile, UserRequest userRequest) {

        User user = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userRequest.getId()));

        if(!multipartFile.isEmpty())
            user.setImgUrl(awsS3Service.saveImageToS3(multipartFile, PATH_IMAGE));
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setNickName(userRequest.getNickName());
        user.setGender(userRequest.getGender());
        user.setFacebook(userRequest.getFacebook());
        user.setZalo(userRequest.getZalo());
        user.setLocal(userRequest.getLocal());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO addUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail()))
            throw new NotFoundException("Email already exists: " + userRequest.getEmail());

        User user = new User();
        userMapper.updateUser(userRequest, user);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setImgUrl(IMAGER_USER_DEFAULT);
        user.setRole(ROLE_DEFAULT);
        user.setPoint(0L);
        user.setCountPost(0L);
        user.setProductLost();
        user.setProductSuccess();
        User savedUser = userRepository.save(user);

//        set nhiem vu
        missionService.addMissionToUser(savedUser);
//        set point khi tao moi => add dich vu
        servicePackageService.addServiceDetails(savedUser.getId(), ID_SERVICE_DEFAULT, true);

        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public void addList(List<UserRequest> list) {
        List<User> users = userMapper.toUserList(list);

        userRepository.saveAll(users.stream().peek(item -> {
            item.setPassword(passwordEncoder.encode(item.getPassword()));
            item.setRole(Role.USER);
        }).collect(Collectors.toList()));
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isExists(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new NotFoundException("User not found with id: " + id)) != null;
    }

    public User findByID(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new NotFoundException("User not found with id: " + id));
    }

    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(()-> new NotFoundException("User not found with email: " + email));
    }
}
