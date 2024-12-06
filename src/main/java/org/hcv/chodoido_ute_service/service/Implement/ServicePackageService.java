package org.hcv.chodoido_ute_service.service.Implement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.dto.request.ServicePackageRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.ServicePackage;
import org.hcv.chodoido_ute_service.entity.ServiceDetails;
import org.hcv.chodoido_ute_service.entity.User;
import org.hcv.chodoido_ute_service.exception.NoActionException;
import org.hcv.chodoido_ute_service.exception.NotFoundException;
import org.hcv.chodoido_ute_service.mapper.ServicePackageMapper;
import org.hcv.chodoido_ute_service.mapper.UserMapper;
import org.hcv.chodoido_ute_service.repository.ServiceDetailsRepository;
import org.hcv.chodoido_ute_service.repository.ServicePackageRepository;
import org.hcv.chodoido_ute_service.repository.UserRepository;
import org.hcv.chodoido_ute_service.service.Interface.IServicePackageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.hcv.chodoido_ute_service.applicationInitConfig.ApplicationInitConfig.SERVICE_PACKAGE_DEFAULT;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServicePackageService implements IServicePackageService {
    ServicePackageRepository servicePackageRepository;
    ServiceDetailsRepository serviceDetailsRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    ServicePackageMapper servicePackageMapper;

    @Override
    public UserDTO addServiceDetails(Long idUser, Long idService, boolean isNewUser) {
        if(!isNewUser && Objects.equals(idService, SERVICE_PACKAGE_DEFAULT.getId()))
            throw new NoActionException("Not add service new user");
        User u = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("Not found user by id: " + idUser));
        ServicePackage servicePackage = this.findServiceById(idService);
        if(u.getPoint() - servicePackage.getPoint() < 0)
           throw new NoActionException("Not enough point");
        ServiceDetails s = ServiceDetails.builder()
                .id(0L).user(u).servicePackage(servicePackage)
                .expiration(LocalDateTime.now().plusSeconds(servicePackage.getTime())).build();

        ServiceDetails saved = this.serviceDetailsRepository.save(s);
        u.setCountPost(servicePackage.getCountPost());
        u.setPoint(-servicePackage.getPoint());
        return userMapper.userToUserDTO(userRepository.save(u));
    }

    @Override
    public ServicePackage addService(ServicePackageRequest servicePackage) {
        ServicePackage s = servicePackageMapper.toServicePackage(servicePackage);
        s.setId(0L);
        return servicePackageRepository.save(s);
    }

    @Override
    public ServiceDetails updateServiceDetails(ServiceDetails serviceDetails) {
        return null;
    }

    @Override
    public ServicePackage updateService(ServicePackage servicePackage) {
        ServicePackage servicePackage1 = this.findServiceById(servicePackage.getId());
        return servicePackageRepository.save(servicePackage);
    }

    @Override
    public void deleteServiceDetails(Long idService) {
        servicePackageRepository.deleteById(idService);
    }

    @Override
    public void deleteService(Long idService) {
        servicePackageRepository.deleteById(idService);
    }

    @Override
    public ServicePackage findServiceById(Long idService) {
        return servicePackageRepository.findById(idService)
                .orElseThrow(()-> new NotFoundException("Not found servicePace"));
    }

    @Override
    public ServiceDetails findServiceDetailsById(Long idServiceDetails) {
        return serviceDetailsRepository.findById(idServiceDetails)
                .orElseThrow(()-> new NotFoundException("Not found serviceDetails"));
    }

    @Override
    public ServicePackage update(ServicePackageRequest servicePackageRequest) {
        ServicePackage servicePackage = servicePackageRepository.findById(servicePackageRequest.getId())
                .orElseThrow(()-> new NotFoundException("Not found service by id: " + servicePackageRequest.getId()));

        servicePackageMapper.updateServicePackage(servicePackageRequest, servicePackage);
        return servicePackageRepository.save(servicePackage);
    }

    @Override
    public int getCountPost(Long idUser) {
        return 0;
    }

    @Override
    public List<ServicePackage> getListServerExpiration(Long idUser, int status) {
        return List.of();
    }

    @Override
    public List<ServicePackage> getListService() {
        return servicePackageRepository.findAll();
    }
}
