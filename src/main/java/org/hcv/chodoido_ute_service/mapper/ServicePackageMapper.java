package org.hcv.chodoido_ute_service.mapper;

import org.hcv.chodoido_ute_service.dto.request.MissionRequest;
import org.hcv.chodoido_ute_service.dto.request.ServicePackageRequest;
import org.hcv.chodoido_ute_service.dto.response.MissionDTO;
import org.hcv.chodoido_ute_service.entity.Mission;
import org.hcv.chodoido_ute_service.entity.ServicePackage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface ServicePackageMapper {
    ServicePackage toServicePackage(ServicePackageRequest servicePackageRequest) ;
    void updateServicePackage(ServicePackageRequest servicePackageRequest, @MappingTarget ServicePackage servicePackage) ;
}
