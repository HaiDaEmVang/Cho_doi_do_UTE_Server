package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.ProductImg;
import org.hcv.chodoido_ute_service.entity.ProductVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVideoRepository extends JpaRepository<ProductVideo, Long> {

}
