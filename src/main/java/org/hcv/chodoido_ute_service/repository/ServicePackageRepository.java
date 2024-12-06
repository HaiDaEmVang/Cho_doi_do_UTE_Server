package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.ServicePackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicePackageRepository extends JpaRepository<ServicePackage,Long> {
    Optional<ServicePackage> findByName(String name);
}
