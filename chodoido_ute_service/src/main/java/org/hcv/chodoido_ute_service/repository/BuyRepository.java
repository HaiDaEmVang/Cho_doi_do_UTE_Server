package org.hcv.chodoido_ute_service.repository;

import org.hcv.chodoido_ute_service.entity.Buy;
import org.hcv.chodoido_ute_service.entity.BuyStatus;
import org.hcv.chodoido_ute_service.entity.Product;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    @Query("select b.product from Buy b where b.user = :user")
    List<Product> findByUser(@Param("user") User user);

    @Query("select count(b) from Buy b where b.user = :user")
    int countProductByUser(@Param("user") User user);

    List<Product> findByUserAndStatus(User user, BuyStatus status);
}
