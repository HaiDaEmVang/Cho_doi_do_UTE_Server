package org.hcv.chodoido_ute_service.service.Interface;

import org.hcv.chodoido_ute_service.dto.request.ServicePackageRequest;
import org.hcv.chodoido_ute_service.dto.response.UserDTO;
import org.hcv.chodoido_ute_service.entity.ServicePackage;
import org.hcv.chodoido_ute_service.entity.ServiceDetails;

import java.util.List;

public interface IServicePackageService {

    UserDTO addServiceDetails(Long idUser, Long idService, boolean isNewUser);
    ServicePackage addService(ServicePackageRequest servicePackage);

    ServiceDetails updateServiceDetails(ServiceDetails serviceDetails);
    ServicePackage updateService(ServicePackage servicePackage);

    void deleteServiceDetails(Long idService);
    void deleteService(Long idService);

    ServicePackage findServiceById(Long idServicePackage);
    ServiceDetails findServiceDetailsById(Long idServiceDetails);

    ServicePackage update(ServicePackageRequest servicePackageRequest);


    int getCountPost(Long idUser);

    List<ServicePackage>  getListServerExpiration(Long idUser, int status); //0: het han , 1 : con han

    List<ServicePackage> getListService();


}
