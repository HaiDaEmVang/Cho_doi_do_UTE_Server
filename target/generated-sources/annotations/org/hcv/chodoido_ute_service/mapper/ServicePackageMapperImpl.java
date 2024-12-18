package org.hcv.chodoido_ute_service.mapper;

import javax.annotation.processing.Generated;
import org.hcv.chodoido_ute_service.dto.request.ServicePackageRequest;
import org.hcv.chodoido_ute_service.entity.ServicePackage;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class ServicePackageMapperImpl implements ServicePackageMapper {

    @Override
    public ServicePackage toServicePackage(ServicePackageRequest servicePackageRequest) {
        if ( servicePackageRequest == null ) {
            return null;
        }

        ServicePackage.ServicePackageBuilder servicePackage = ServicePackage.builder();

        servicePackage.id( servicePackageRequest.getId() );
        servicePackage.name( servicePackageRequest.getName() );
        servicePackage.description( servicePackageRequest.getDescription() );
        servicePackage.time( servicePackageRequest.getTime() );
        servicePackage.point( servicePackageRequest.getPoint() );
        servicePackage.countPost( (long) servicePackageRequest.getCountPost() );

        return servicePackage.build();
    }

    @Override
    public void updateServicePackage(ServicePackageRequest servicePackageRequest, ServicePackage servicePackage) {
        if ( servicePackageRequest == null ) {
            return;
        }

        servicePackage.setId( servicePackageRequest.getId() );
        servicePackage.setName( servicePackageRequest.getName() );
        servicePackage.setDescription( servicePackageRequest.getDescription() );
        servicePackage.setTime( servicePackageRequest.getTime() );
        servicePackage.setPoint( servicePackageRequest.getPoint() );
        servicePackage.setCountPost( (long) servicePackageRequest.getCountPost() );
    }
}
