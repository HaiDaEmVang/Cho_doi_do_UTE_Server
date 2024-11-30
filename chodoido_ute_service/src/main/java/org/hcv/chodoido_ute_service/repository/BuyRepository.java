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
    @Query("select b from Buy b where b.user = :user")
    List<Buy> findByUserBought(@Param("user") User user);

    @Query("select b from Buy b where b.user = :user and b.product = :product")
    Buy findByUserAndProduct(@Param("user") User user, @Param("product") Product product);

    @Query("select b from Buy b where b.product.user = :user")
    List<Buy> findByUser(@Param("user") User user);

    @Query("select count(b) from Buy b where b.user = :user")
    int countProductByUser(@Param("user") User user);

    List<Product> findByUserAndStatus(User user, BuyStatus status);
}
